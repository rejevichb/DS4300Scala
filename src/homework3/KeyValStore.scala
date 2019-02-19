package homework3

import scala.collection.immutable.HashMap
import language.{postfixOps, implicitConversions}

//We literally copied & pasted method signatures directly from Redis documentation

class KeyValStore {

  //we represent the key value store as a map
  private var core_map: scala.collection.immutable.HashMap[String, List[String]] = HashMap()


  /*
  TODO
   */
  def get(key: String): Option[List[String]] = core_map get key //coremap(k)

  /*
  Set a new key value pair in the store, or update it if it already exists. A
  key has a list of strings as a value, and multiple string values can be added
  to the list at once.
   */
  def set(key: String, value: String): Unit = {
    this.core_map = this.core_map + (key -> List(value))
  }

  /*
  Insert the specified value at the head of the list stored at key.
   */
  def lpush(key: String, value: String): Int = {
    this.core_map = this.core_map + (key -> (value +: (this.core_map getOrElse (key, List("")))))
    this.core_map getOrElse(key, List("")) size
  }


  /*
  Insert all the specified values at the tail of the list stored at key.
  Returns the new length of the list
   */
  def rpush(key: String, value: String): Int = {
    this.core_map = this.core_map + (key -> (this.core_map getOrElse (key, List("") :+ value)))
    this.core_map getOrElse(key, List("")) length
  }

  /*
  Removes and returns the first element of the list stored at key.
   */
  def lpop(key: String): String = {
    val valueList = this.core_map getOrElse(key, "")

  }

  def rpop(key: String): String

  def lrange(key: String): List[String]

  def llen(key: String)

  def flushall() = this.core_map = HashMap()

}




