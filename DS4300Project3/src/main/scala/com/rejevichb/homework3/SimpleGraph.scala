package com.rejevichb.homework3

import language.postfixOps
import scala.collection.immutable.{HashMap, HashSet, Queue}
import scala.collection.breakOut



class SimpleGraph {
  type Graph = Map[Node, List[Node]]
  type Node = String
  val kvs: KeyValStore[Node, Node] = new KeyValStore[Node, Node]
  val nodes: List[Node] = List()

  def addNode(v: Node) = {
    if (this.nodes contains v) throw new Error("duplicate nodes")
    this.nodes +: v
  }

  def addEdge(u: Node, v: Node) = {
    if (!(this.nodes contains v)) addNode(v)
    if (!(this.nodes contains u)) addNode(u)
    kvs.set(u, v)
    kvs.set(v, u)
  }

  def adjacent(v: Node): List[Node] = {
    kvs.getOrElse(v, List.empty)
  }

  def flush(): Unit = this.kvs.flushall();


  def shortest_path(start: Node): List[List[Node]] = {
    val g: Map[Node, List[Node]] = this.kvs.all()

    def BFS(elems: List[Node], visited: List[List[Node]]): List[List[Node]] = {
      val newNeighbors = elems.flatMap(g(_)).filterNot(visited.flatten.contains).distinct
      if (newNeighbors.isEmpty)
        visited
      else
        BFS(newNeighbors, newNeighbors :: visited)
    }

    BFS(List(start), List(List(start))).reverse
  }
}

//
//
//  def shortestPath(src: Node, v: Node): Map[Node, Int] = {
//    //create a map of node to distance from source
//    var tupleList = nodes zip Stream.continually(0)
//    var distanceMap:Map[Node,Int] = (for((i,j) <- tupleList) yield i->j)(breakOut)
//
//    val queue = collection.mutable.Queue[Node]()
//
//    queue += src
//    var seen = new HashSet[Node]
//    seen = seen + src
//
//    while (queue.nonEmpty) {
//      val cur: Node = queue.dequeue()
//      for {
//        node <- adjacent(cur)
//        if !seen.contains(node)
//      } yield {
//        queue.enqueue(node)
//        seen = seen + node  // add to the visited set.
//        val d:Int = distanceMap getOrElse (cur, 0)
//        distanceMap = distanceMap updated (cur, d + 1)
//      }
//    }
//    Console.println(distanceMap)
//    distanceMap
//    }
//}
