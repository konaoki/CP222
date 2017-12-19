import java.util.ArrayList;
/**
* Interface for a directed graph data structure
*/
public class Graph<N, W> implements IGraph<N, W> {
  ArrayList<INode<N>> nodes;
  ArrayList<INode<N>> neighbors;
  ArrayList<IEdge<N,W>> edges;
  ArrayList<IEdge<N,W>> edgesFrom;
  ArrayList<IEdge<N,W>> edgesTo;
  public Graph(){
    nodes=new ArrayList<INode<N>>();
    neighbors=new ArrayList<INode<N>>();
    edges=new ArrayList<IEdge<N,W>>();
    edgesFrom=new ArrayList<IEdge<N,W>>();
    edgesTo=new ArrayList<IEdge<N,W>>();
  }
  /**
  * Gets an array of all the nodes in the graph
  * @return the node set
  */
  public INode<N>[] getNodeSet()
  {
    INode<N>[] nodeSet = new Node[nodes.size()];
    for(int i=0; i<nodes.size(); i++)
    {
      nodeSet[i]=nodes.get(i);
    }
    return nodeSet;
  }

  /**
  * An array of the neighbors of a node
  * @param n the node
  * @return neighbors of n
  */
  public INode<N>[] getNeighbors(INode<N> n)
  {
    ArrayList<INode<N>> neibs = new ArrayList<INode<N>>();
    IEdge[] edgesFromArray = getEdgesFrom(n);
    for(int i=0; i<edgesFromArray.length; i++)
    {
      neibs.add(edgesFromArray[i].getDestination());
    }
    INode[] neibsArray = new Node[neibs.size()];
    for(int i=0; i<neibs.size(); i++)
    {
      neibsArray[i]=neibs.get(i);
    }
    return neibsArray;
  }

  /**
  * Adds a node to the graph
  * @param v value at the node
  * @return the newly added node
  */
  public INode<N> addNode(N v)
  {
    boolean isAdd=true;
    INode<N> n = new Node<N>(v);
    for(int i=0; i<nodes.size();i++)
    {
      if(n.getValue().equals(nodes.get(i).getValue()))
      {
        isAdd=false;
        n=nodes.get(i);
      }
    }
    if(isAdd)
    {
      nodes.add(n);

    }
    return n;

  }

  /**
  * Gets an array of all the edges in the graph
  * @return the edge set
  */
  public IEdge<N,W>[] getEdgeSet()
  {
    IEdge<N,W>[] edgeSet = new Edge[edges.size()];
    for(int i=0; i<edges.size(); i++)
    {
      edgeSet[i]=edges.get(i);
    }
    return edgeSet;
  }

  /**
  * Gets an array of all the edges sourced at a particular node
  * @param n the source node
  * @return the edge set
  */
  public IEdge<N,W>[] getEdgesFrom(INode<N> n)
  {
    ArrayList<IEdge<N,W>> edgesFrom=new ArrayList<IEdge<N,W>>();
    for(int i=0; i<edges.size(); i++)
    {
      if(edges.get(i).getSource()==n)
      {
        edgesFrom.add(edges.get(i));
      }
    }
    IEdge[] edgesFromArray = new Edge[edgesFrom.size()];
    for(int i=0; i<edgesFrom.size(); i++)
    {
      edgesFromArray[i]=edgesFrom.get(i);
    }
    return edgesFromArray;
  }

  /**
  * Gets an array of all the edges destined for a particular node
  * @param n the destination node
  * @return the edge set
  */
  public IEdge<N,W>[] getEdgesTo(INode<N> n)
  {
    ArrayList<IEdge<N,W>> edgesTo = new ArrayList<IEdge<N,W>>();
    for(int i=0; i<edges.size(); i++)
    {
      if(edges.get(i).getDestination()==n)
      {
        edgesTo.add(edges.get(i));
      }
    }
    IEdge[] edgesToArray = new Edge[edgesTo.size()];
    for(int i=0; i<edgesTo.size(); i++)
    {
      edgesToArray[i]=edgesTo.get(i);
    }
    return edgesToArray;
  }



  /**
  * Adds an edge to the graph.
  * Duplicate edges are not allowed in the graph. The equals method of the edge can
  * be used to determine if two edges duplicate one another.
  * @param w weight of the edge
  * @param s source node
  * @param d destination node
  */
  public void addEdge(INode<N> s, INode<N> d, W w)
  {
    //System.out.println(s.getValue()+" "+d.getValue());
    IEdge e = new Edge<N,W>(s,d,w);
    boolean isAdd=true;
    for(int i=0; i<edges.size(); i++)
    {
      if(edges.get(i).equals(e))
      {
        isAdd=false;
      }
    }
    if(isAdd)
    {
      edges.add(e);
    }

  }
  public void printNodes()
  {
    for(int i=0; i<nodes.size();i++)
    {
      System.out.println(nodes.get(i).getValue());
    }
  }
}
