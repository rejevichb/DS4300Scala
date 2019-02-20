package com.rejevichb.homework3

import scala.collection.immutable.HashMap
import language.{postfixOps, implicitConversions}

//We literally copied & pasted method signatures directly from Redis documentation
class KeyValStore[keyT, valT] {

  //we represent the key value store as a map of key String to value: List of type T.
  private var core_map: scala.collection.immutable.HashMap[keyT, List[valT]] = HashMap()
  /*
  Only the current instance of KeyValStore can access its own core map. (i.e. if we were to extend
  the class to accept another instance of itself, the other instance would not have access to the
  other's core map. This is object-private, even more restrictive than Java's private modifier */
  private[this] def updateMap(map : HashMap[keyT, List[valT]]) : Unit = this.core_map = map
  /*
  Returns the list of strings a the given key or the empty list if not found. */
  private[this] def lookup(k:keyT): List[valT] = this.core_map getOrElse(k, List.empty)
  /*
  Returns the list of string values at the given key, and a list with a single empty string i */
  def get(key: String): Option[List[valT]] = core_map get key
  /*
  Allows the specification of a variable orElse value for a get*/
  def getOrElse(key: keyT, orElse: List[valT]) : List[valT] = core_map getOrElse(key, orElse)
  /*
  Return the entire hash-map (no need to copy - its immutable) */
  def all() : HashMap[keyT, List[valT]] = this.core_map
  /*
  Set a new key value pair in the store, or update it if it already exists. A
  key has a list of strings as a value, and multiple string values can be added
  to the list at once. */
  def set(key: keyT, value: valT): Unit = {
    updateMap(this.core_map + (key -> List(value)))
  }
  /*
  Insert the specified value at the head of the list stored at key. */
  def lpush(key: keyT, value: valT): Int = {
    updateMap(this.core_map + (key -> (value +: lookup(key))))
    llen(key)
  }
  /*
  Insert all the specified values at the tail of the list stored at key.
  Returns the new length of the list */
  def rpush(key: keyT, value: valT): Int = {
    updateMap(this.core_map + (key -> (lookup(key) :+ value)))
    llen(key)
  }
  /*
  Removes and returns the first element of the list stored at key.  */
  def lpop(key: keyT): valT = {
    val valueList = lookup(key)
    updateMap(this.core_map updated (key, valueList.drop(1)))
    valueList.head
  }
  /*
  Removes and returns the last element of the list stored at key.  */
  def rpop(key: keyT): valT = {
    val valueList = lookup(key)
    updateMap(this.core_map updated (key, valueList.dropRight(1)))
    valueList.last
  }
  /*
  Returns the specified elements of the list stored at key. The offsets start and
  stop are zero-based indexes, with 0 being the first element of the list to n. */
  def lrange(k: keyT, maxIdx:Int, minIdx:Int): List[valT] = {
    val l = lookup(k)
    //assert(maxIdx >= minIdx && maxIdx <= (l length) && minIdx >= 0, "invalid min or max argument. list size ")
    for {
      (x: valT, i: Int) <- l zipWithIndex //tried without explicit typing
      if (i <= maxIdx) && (i >= minIdx) //tried indenting if
    } yield x
  }
  /*
  Returns the length of the list stored at key. If key does not
  exist, it is interpreted as an empty list and 0 is returned. */
  def llen(key: keyT): Int = lookup(key) length

  /*
  Delete all the keys in this instance of KeyValueStore */
  def flushall() : Unit = this.core_map = HashMap()

}




