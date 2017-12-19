/**
 * Interface for searching graphs
 */
public class DepthFirstSearcher<N,W> implements ISearcher<N,W> {
    boolean b;
    IList<INode<N>> l;

    public DepthFirstSearcher()
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
        b=false;
        for(int i=0; i<g.getEdgeSet().length; i++)
        {
          g.getEdgeSet()[i].setVisited(false);
        }
        for(int i=0; i<g.getNodeSet().length; i++)
        {
          g.getNodeSet()[i].setVisited(false);
        }
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
        b=false;
        for(int i=0; i<g.getEdgeSet().length; i++)
        {
          g.getEdgeSet()[i].setVisited(false);
        }
        for(int i=0; i<g.getNodeSet().length; i++)
        {
          g.getNodeSet()[i].setVisited(false);
        }
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
        b=false;
        for(int i=0; i<g.getEdgeSet().length; i++)
        {
          g.getEdgeSet()[i].setVisited(false);
        }
        for(int i=0; i<g.getNodeSet().length; i++)
        {
          g.getNodeSet()[i].setVisited(false);
        }
        if(s!=e)
        {

            helper(g,s,e,s);
            for(int i=0; i<l.size(); i++)
            {
                System.out.println(l.fetch(i).getValue());
            }
        }
    }
    public void helper(IGraph<N,W> g, INode<N> s, INode<N> e,INode<N> os)
    {
        //System.out.println("node: "+s.getValue()+" num edges from: "+g.getEdgesFrom(s).length);

        for(int i=0; i<g.getEdgesFrom(s).length; i++)
        {
            if(!g.getEdgesFrom(s)[i].getVisited() && !g.getEdgesFrom(s)[i].getDestination().getVisited())
            {
                    //if the the edge and node still hasn't been visited
                g.getEdgesFrom(s)[i].setVisited(true);
                g.getEdgesFrom(s)[i].getDestination().setVisited(true);
                if(g.getEdgesFrom(s)[i].getDestination() != e)
                {
                    helper(g,g.getEdgesFrom(s)[i].getDestination(), e,os);
                        //if it still hasn't found the end, keep going
                }
                else
                {
                    //System.out.println("ye");
                    b=true;
                        //when it has found the destination
                    boolean traverseOver=false;
                    INode<N> curr = g.getEdgesFrom(s)[i].getDestination();
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

            }
            else
                {
                    //System.out.println("current: "+s.getValue());
                    //System.out.println("this has been visited: "+g.getEdgesFrom(s)[i].getDestination().getValue());
                }

        }


    }

}
