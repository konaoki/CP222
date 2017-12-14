public class Node<N> implements INode<N> {
  N value;
  // Implementors should provide a constructor that takes in a single argument, the
  // value for the node to initially hold.
  public Node(N value)
  {
    this.value=value;
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


  // No equals method should be implemented for Nodes since nodes are only equal if
  // they are exactly the same instance (which is the default behavior for objects
  // in java.
}
