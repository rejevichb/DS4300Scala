package com.rejevichb.homework3

import scala.collection.immutable.HashMap
import language.postfixOps

class LabeledPropertyGraph {

  /*
  A labeled-property graph model is represented by a set of nodes, relationships, properties,
  and labels. Both nodes of data and their relationships are named and can store properties
  represented by key/value pairs. Nodes can be labelled to be grouped. The edges representing
  the relationships have two qualities: they always have a start node and an end node, and are
  directed;[6] making the graph a directed graph. Relationships can also have properties. This
  is useful in providing additional metadata and semantics to relationships of the nodes. Direct
  storage of relationships allows a constant-time traversal.

  Since the assignment did not specify implementation details of the graph, i.e whether the
  edges are directed or not (the example they are not, but in LPG, which is the most popular
  form of graph model as of 2018, and the model for the most popular graph database as of
  October 2018, Neo4j.

  Implementation based on: https://algs4.cs.princeton.edu/44sp/

   */

  /*
  Type Aliases and Final Class Representing the Following:
    : Attributes are strings and Vertex's are ints.
    : An Edge is immutable and contains a toVertex, fromVertex and weight stored as a double
    : a Wei
 */
  type Attribute = String
  type Vertex = Int
  final case class Edge(from: Vertex, to: Vertex, weight: Double)
  private var attributes : Map[Vertex, List[Attribute]] = new HashMap()
  private val adj: KeyValStore[Vertex, Edge] = new KeyValStore() //Adjacency list for Vertex
  private var vertexes: scala.collection.immutable.List[Vertex] = List()
  /*
  Adds a bare (unconnected) vertex to the graph */
  def addVertex(v: Vertex) : Unit = {
    this.vertexes = this.vertexes :+ v
  }
  /*
  Add src and sink nodes to  */
  def createIfNotPresent(src: Vertex, sink: Vertex): Unit = {
    if (!(this.vertexes contains src)) addVertex(src)
    if (!(this.vertexes contains sink)) addVertex(sink)
  }
  /*
  Add a uni-directional edge to the graph from vertex u to v */
  def addEdge(src: Vertex, sink: Vertex, weight: Double) : Unit = {
    createIfNotPresent(src, sink)
    this.adj.set(src, Edge(src,sink,weight))
  }
  /*
  Add a bi-directional edge to the graph between vertexes u and v */
  def addBiDirectionalEdge(src: Vertex, sink: Vertex, weightSrcSink:Int, weightSinkSrc:Int) : Unit = {
    createIfNotPresent(src, sink)
    createIfNotPresent(sink,src)
    this.adj.set(src, Edge(src,sink,weightSrcSink))
    this.adj.set(sink, Edge(sink,src,weightSinkSrc))
  }
  /*
  List all the vertexes that this vertex flows to */
  def adjacentTo(v: Vertex): List[Vertex] =
    for { edges <- this.adj.getOrElse(v, List.empty) } yield edges.to


  /*
  Return vertexes in this list flowing to the given vertex */
  def vertexesFlowingTo(v: Vertex, edgs:List[Edge]): List[Vertex] = {
    for {e <- edgs if e.to == v}  yield e.from
  }

  /*
  List all the vertexes that flow to this vertex */
  def adjacentFrom(v: Vertex): List[Vertex] = {
    val adjacencyList = List()
    for (edg <- adj.all()) {
      if (vertexesFlowingTo(v, edg._2).nonEmpty) adjacencyList :+ vertexesFlowingTo(v, edg._2)
    }
    adjacencyList
  }

  /*
  List all vertexes either adjacent to or from given vertex */
  def adjacentAll(v: Vertex): List[Vertex] = {
    adjacentTo(v) ::: adjacentFrom(v)
  }


//  def shortestPath(src: Vertex, sink: Vertex): List[Vertex] = {
//
//  }

  //some intuitive and useful one liners
  def outDegree(v:Vertex) : Int = adjacentTo(v) length
  def inDegree(v:Vertex) : Int = adjacentFrom(v) length
  def isolated(v:Vertex) : Boolean =  outDegree(v) < 1  && inDegree(v) < 1
  def isAdjacentTo(a:Vertex, b:Vertex) : Boolean = adjacentTo(a) contains b
  def isAdjacentFrom(a:Vertex, b:Vertex) : Boolean = adjacentFrom(a) contains b
}
