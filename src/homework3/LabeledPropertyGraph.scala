package homework3

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

   */

  private var attributes : Map[Vertex, List[Attribute]] = new HashMap()
  private val relationships: KeyValStore = new KeyValStore()
  private var vertexes: scala.collection.immutable.List[Vertex] = List()
  type Vertex = String
  type Attribute = Double

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
  def addEdge(src: Vertex, sink: Vertex) : Unit = {
    createIfNotPresent(src, sink)
    this.relationships.set(src,sink)
  }

  /*
  Add a bi-directional edge to the graph between vertexes u and v */
  def addBiDirectionalEdge(src: Vertex, sink: Vertex) : Unit = {
    this.relationships.set(src,sink)
    this.relationships.set(sink,src)
  }

  /*
  List all the vertexes that this vertex flows to */
  def adjacentTo(v: Vertex): List[Vertex] = {
    this.relationships.getOrElse(v, List.empty)
  }
  /*
  List all the vertexes that flow to this vertex */
  def adjacentFrom(v: Vertex): List[Vertex] = {
    val adjacencyList = List()
    for (node <- relationships.all()) {
      if (node._2 contains v) adjacencyList :+ node
    }
    adjacencyList
  }

  /*
  List all vertexes either adjacent to or from given vertex */
  def adjacentAll(v: String): List[String] = {
    adjacentTo(v) ::: adjacentFrom(v)
  }


  def shortestPath(src: Vertex, sink: Vertex): List[Vertex] = {
    List(List.empty)
  }

  //some intuitive and useful one liners
  def outDegree(v:Vertex) : Int = adjacentTo(v) length
  def inDegree(v:Vertex) : Int = adjacentFrom(v) length
  def isolated(v:Vertex) : Boolean =  outDegree(v) < 1  && inDegree(v) < 1
  def isAdjacentTo(a:Vertex, b:Vertex) : Boolean = adjacentTo(a) contains b
  def isAdjacentFrom(a:Vertex, b:Vertex) : Boolean = adjacentFrom(a) contains b
}
