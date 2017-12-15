/**
* Interface for an edge in a graph. Edges are immutable.
*/
public class Edge<N,W>{
  Node<N> source;
  Node<N> destination;
  W weight;

  // Implementors should implement a construct that takes in the
  // source, destination, and weight
  public Edge(Node<N> source,Node<N> destination, W weight)
  {
    this.source=source;
    this.destination=destination;
    this.weight=weight;
  }
  /**
  * The source node of the edge
  * @return the source node
  */
  public Node<N> getSource()
  {
    return source;
  }

  /**
  * The destination node of the edge
  * @return the destination node
  */
  public Node<N> getDestination()
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
    Edge oe = (Edge)o;
    return (source==oe.getSource() && destination==oe.getDestination())?true:false;
  }
}
