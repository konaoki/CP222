import java.util.ArrayList;
public class Node<N>{
  N value;
  Node<N> nextNode; //node at edge destination
  Node<N> parentNode; //node before one radial step in breadth first gradient map generation
  String material = ""; //is it a wall or vaccum
  ArrayList<Edge<N,Integer>> edgeSet;
  // Implementors should provide a constructor that takes in a single argument, the
  // value for the node to initially hold.
  public Node(N value)
  {
    this.value=value;
    edgeSet=new ArrayList<Edge<N,Integer>>();
  }
  /**
  * Updates the value at the node
  * @param v new value
  */
  public void setValue(N v)
  {
    value=v;
  }

  /**
  * Fetches the value at the node
  * @return the current value
  */
  public N getValue()
  {
    return value;
  }

  public Edge<N,Integer>[] getEdgeSet()
  {
    Edge<N,Integer>[] e = new Edge[edgeSet.size()];
    for(int i=0; i<e.length; i++)
    {
      e[i] = edgeSet.get(i);
    }
    return e;
  }


  // No equals method should be implemented for Nodes since nodes are only equal if
  // they are exactly the same instance (which is the default behavior for objects
  // in java.
}
