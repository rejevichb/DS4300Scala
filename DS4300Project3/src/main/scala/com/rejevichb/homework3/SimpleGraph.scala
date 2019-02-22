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

  //add bi-directional edge to graph
  def addEdge(u: Node, v: Node) = {
    if (!(this.nodes contains v)) addNode(v)
    if (!(this.nodes contains u)) addNode(u)
    kvs.set(u, v)
    kvs.set(v, u)
  }

  //list adjacent nodes
  def adjacent(v: Node): List[Node] =  kvs.getOrElse(v, List.empty)

  //flush in-memory store
  def flush() : Unit = this.kvs.flushall

  //at each iteration of the inner BFS loop, look at all new nodes we see
  //starting from src and add them to the 'seen' list. Return adjacency matrix
  //in List[List[Node]] format, where the length of the inner list is the length
  //of the path from given src to the last node in the list.
  def shortest_path(src: Node): List[List[Node]] = {
    val g: Map[Node, List[Node]] = this.kvs.all()
    def BFS(nds: List[Node], seen: List[List[Node]]): List[List[Node]] = {
      val newNeighbors = nds.flatMap(g(_)).filterNot(seen.flatten.contains).distinct
      if (newNeighbors.isEmpty) seen
      else BFS(newNeighbors, newNeighbors :: seen)
    }
    BFS(List(src), List(List(src))).reverse
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
