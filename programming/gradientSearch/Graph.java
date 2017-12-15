import java.util.ArrayList;
/**
* Interface for a directed graph data structure
*/
public class Graph<N, W>{
  ArrayList<Node<N>> nodes;
  ArrayList<Node<N>> neighbors;
  ArrayList<Edge<N,W>> edges;
  ArrayList<Edge<N,W>> edgesFrom;
  ArrayList<Edge<N,W>> edgesTo;
  int goalNodeID;
  int width=0;
  int height=0;
  public Graph(){
    nodes=new ArrayList<Node<N>>();
    neighbors=new ArrayList<Node<N>>();
    edges=new ArrayList<Edge<N,W>>();
    edgesFrom=new ArrayList<Edge<N,W>>();
    edgesTo=new ArrayList<Edge<N,W>>();
  }
  /**
  * Gets an array of all the nodes in the graph
  * @return the node set
  */
  public Node<N>[] getNodeSet()
  {
    Node<N>[] nodeSet = new Node[nodes.size()];
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
  public Node<N>[] getNeighbors(Node<N> n)
  {
    ArrayList<Node<N>> neibs = new ArrayList<Node<N>>();
    Edge[] edgesFromArray = getEdgesFrom(n);
    for(int i=0; i<edgesFromArray.length; i++)
    {
      neibs.add(edgesFromArray[i].getDestination());
    }
    Node[] neibsArray = new Node[neibs.size()];
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
  public Node<N> addNode(N v)
  {
    boolean isAdd=true;
    Node<N> n = new Node<N>(v);
    for(int i=0; i<nodes.size();i++)
    {
      if(n.getValue().equals(nodes.get(i).getValue()))
      {
        isAdd=false;
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
  public Edge<N,W>[] getEdgeSet()
  {
    Edge<N,W>[] edgeSet = new Edge[edges.size()];
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
  public Edge<N,W>[] getEdgesFrom(Node<N> n)
  {
    ArrayList<Edge<N,W>> edgesFrom=new ArrayList<Edge<N,W>>();
    for(int i=0; i<edges.size(); i++)
    {
      if(edges.get(i).getSource()==n)
      {
        edgesFrom.add(edges.get(i));
      }
    }
    Edge[] edgesFromArray = new Edge[edgesFrom.size()];
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
  public Edge<N,W>[] getEdgesTo(Node<N> n)
  {
    ArrayList<Edge<N,W>> edgesTo = new ArrayList<Edge<N,W>>();
    for(int i=0; i<edges.size(); i++)
    {
      if(edges.get(i).getDestination()==n)
      {
        edgesTo.add(edges.get(i));
      }
    }
    Edge[] edgesToArray = new Edge[edgesTo.size()];
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
  public void addEdge(Node<N> s, Node<N> d, W w)
  {
    if(s!=d)
    {
          //System.out.println(s.getValue()+" "+d.getValue());
      Edge e = new Edge<N,W>(s,d,w);
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
        s.nextNode=d;
      }
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
