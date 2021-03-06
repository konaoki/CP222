/**
 * Interface for searching graphs
 */
public class BreadthFirstSearcher<N,W> implements ISearcher<N,W> {
  boolean b=false;
  IList<INode<N>> l;
  RingQueue<INode<N>> rq;

  public BreadthFirstSearcher()
  {
    b=false;
    l=new DoubleLinkList<INode<N>>();
  }
    /**
     * Determines if there is a path without returning the path
     * @param g the graph to search in
     * @param s node to start from
     * @param e node to end at
     * @return if a path of any length exists
     */
    public boolean pathExists(IGraph<N,W> g, INode<N> s, INode<N> e)
    {
      l=new DoubleLinkList<INode<N>>();
      rq = new RingQueue<INode<N>>(g.getNodeSet().length);
      b=false;
      helper(g,s,e,s);
      return b;
    }

    /**
     * Finds a path based on the properties of the search algorithm.
     * If there is no path in graph g from node s to node e, null should be
     * returned. If node s and node e are the same, an empty list should be returned.
     * @param g the graph to search in
     * @param s node to start from
     * @param e node to end at
     * @return the list of nodes in the path
     */
    public IList<INode<N>> getPath(IGraph<N,W> g, INode<N> s, INode<N> e)
    {
      l=new DoubleLinkList<INode<N>>();
      rq = new RingQueue<INode<N>>(g.getNodeSet().length);
      b=false;
      if(s==e)
      {
          return new DoubleLinkList();
      }
      else
      {
          helper(g,s,e,s);
          return l;
      }
    }

    public void printPath(IGraph<N,W> g, INode<N> s, INode<N> e)
    {
        l=new DoubleLinkList<INode<N>>();
        rq = new RingQueue<INode<N>>(g.getNodeSet().length);
        b=false;
        if(s!=e)
        {
            helper(g,s,e,s);
            for(int i=0; i<l.size(); i++)
            {
                System.out.println(l.fetch(i).getValue());
            }
        }
    }

    private void helper(IGraph<N,W> g, INode<N> s, INode<N> e, INode<N> os)
    {
      for(int i=0; i<g.getEdgeSet().length; i++)
      {
        g.getEdgeSet()[i].setVisited(false);
      }
      for(int i=0; i<g.getNodeSet().length; i++)
      {
        g.getNodeSet()[i].setVisited(false);
      }
      rq.enqueue(s);
      boolean isFound=false;
      INode<N> n;
      int count = 0;
      while(!isFound)
      {

        n = rq.dequeue();
        //System.out.println(g.getEdgesFrom(n)[0].getDestination().getValue());
        if(n != e)
        {
          for(int i=0; i<g.getEdgesFrom(n).length; i++)
          {
            if(!g.getEdgesFrom(n)[i].getVisited() && !g.getEdgesFrom(n)[i].getDestination().getVisited())
            {
              g.getEdgesFrom(n)[i].setVisited(true);
              g.getEdgesFrom(n)[i].getDestination().setVisited(true);
              rq.enqueue(g.getEdgesFrom(n)[i].getDestination());
            }

          }
        }
        else
        {
          isFound=true;
          //System.out.println("ye");
          b=true;
              //when it has found the destination
          boolean traverseOver=false;
          INode<N> curr = n;
          l.append(curr);
          while(!traverseOver)
          {
              for(int j=0; j<g.getEdgesTo(curr).length; j++)
              {
                  if(g.getEdgesTo(curr)[j].getVisited())
                  {
                      curr=g.getEdgesTo(curr)[j].getSource();
                      l.append(curr);
                      //System.out.println("hi");
                      if(curr==os)
                      {
                          traverseOver=true;
                      }
                  }
              }
          }
          IList<INode<N>> temp=new DoubleLinkList<INode<N>>();
          //reverse list order
          for(int j=l.size()-1; j>=0; j--)
          {
              temp.append(l.fetch(j));
          }
          l=temp;
        }

        count++;
        if(count > g.getNodeSet().length)
        {
          isFound=true;
        }

      }

    }
}
