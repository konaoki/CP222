/**
* Interface for an edge in a graph. Edges are immutable.
*/
public class Edge<N,W> implements IEdge<N,W>{
  INode<N> source;
  INode<N> destination;
  W weight;

  boolean visited = false;
  // Implementors should implement a construct that takes in the
  // source, destination, and weight
  public Edge(INode<N> source,INode<N> destination, W weight)
  {
    this.source=source;
    this.destination=destination;
    this.weight=weight;
  }

  public boolean getVisited()
  {
    return visited;
  }

  public void setVisited(boolean v)
  {
    visited = v;
  }
  /**
  * The source node of the edge
  * @return the source node
  */
  public INode<N> getSource()
  {
    return source;
  }

  /**
  * The destination node of the edge
  * @return the destination node
  */
  public INode<N> getDestination()
  {
    return destination;
  }

  /**
  * The weight of the edge
  * @return the weight
  */
  public W getWeight()
  {
    return weight;
  }

  /**
  * Test for equality of two edges.
  * Edges are equal when the node instances are exactly the same; i.e. this.src==o.src
  * and this.dst == o.dst
  * @param o the other edge
  * @return true if the edges are the same
  */
  public boolean equals(Object o)
  {
    IEdge oe = (Edge)o;
    return (source==oe.getSource() && destination==oe.getDestination())?true:false;
  }
}
