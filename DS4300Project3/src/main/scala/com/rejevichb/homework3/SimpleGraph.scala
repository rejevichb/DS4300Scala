package com.rejevichb.homework3

import language.postfixOps
import scala.collection.immutable.{HashMap, HashSet, Queue}

class SimpleGraph {

  type Node = String
  val kvs : KeyValStore[Node, Node] = new KeyValStore[Node, Node]
  val nodes: List[Node] = List()

  def addNode(v: Node) =  {
    if (this.nodes contains v) throw new Error("duplicate nodes")
    this.nodes +: v
  }
  
  def addEdge(u: Node, v: Node) = {
    if (!(this.nodes contains v)) addNode(v)
    if (!(this.nodes contains u)) addNode(u)
    kvs.set(u,v)
    kvs.set(v,u)
  }
  
  def adjacent(v: Node): List[Node] = {
    for {
      entry <- this.kvs.all
      x <- if (entry._1 == v) entry._2.head
    } yield x
  }
  
  def shortestPath(src: Node, v: Node): Map[Node, Int] = {
    val distanceMap = (nodes zip Stream.continually(-1000)).toMap
    val queue = collection.mutable.Queue[Node]()

    queue :+ src

    val seen = new HashSet[Node]
    seen +: src

    while (queue.nonEmpty) {
      val cur: Node = queue.dequeue()
      for {
        node <- adjacent(cur)
        if !seen.contains(node)
      } yield {
        queue :+ node
        seen +: node  // add to the visited set.
        distanceMap(node) = distanceMap(node) + 1
      }
    }
    distanceMap
    }
}
