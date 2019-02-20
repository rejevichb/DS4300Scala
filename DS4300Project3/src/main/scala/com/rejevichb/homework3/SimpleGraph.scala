package homework3

import language.postfixOps

class SimpleGraph {

  type Node = String
  val kvs : KeyValStore[Node, Node] = new KeyValStore[Node, Node]
  val nodes: List[Node] = List()

  def addNode(v: Node) = this.nodes +: v
  
  def addEdge(u: Node, v: Node) = {
    if (!(this.nodes contains v)) addNode(v)
    if (!(this.nodes contains u)) addNode(u)
    kvs.set(u,v)
  }
  
  def adjacent(v: Node): List[Node] = {
    //kvs.all().foldLeft(List[Node]){ case (a, (k, v)) => a +: v.head }
    for {
      entry <- this.kvs.all
      x <- if (entry._2.head == v) entry._1
      x <- if (entry._1 == v) entry._2.head
    } yield x
  }
  
  def shortestPath(u: Node, v: Node): List[Node] = {

  }
  
  


}
