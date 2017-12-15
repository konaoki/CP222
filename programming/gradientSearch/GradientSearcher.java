import java.util.ArrayList;
public class GradientSearcher{
	GraphReader r;
	Graph<Integer,Integer> g;
	Boolean[] isVisited;
	RingQueue<Node<Integer>> queue;
	Node<Integer> goal;
	public GradientSearcher()
	{
		r = new GraphReader();
		g = r.read("graphfile.cs2");
		isVisited = new Boolean[g.width*g.height];
		for(int i=0; i<isVisited.length; i++)
		{
			isVisited[i]=false;
		}
		queue = new RingQueue<Node<Integer>>(g.width*g.height);
		goal=g.getNodeSet()[g.goalNodeID];
	}
	public int[] search(Node<Integer> n)
	{
		boolean isGoalFound=false;
		Edge<Integer,Integer>[] edgesFrom;
		ArrayList<Integer> path = new ArrayList<Integer>();
		int[] pathAr = new int[0];
		while(!isGoalFound)
		{
			edgesFrom = g.getEdgesFrom(n);
			int minIdx=0;
			for(int i=0; i<edgesFrom.length; i++)
			{
				if(edgesFrom[i].getWeight() <  edgesFrom[minIdx].getWeight())
				{
					minIdx=i;
				}
			}
			
			n=edgesFrom[minIdx].getDestination();
			System.out.println("to node: "+n.getValue()+" edge weight: "+edgesFrom[minIdx].getWeight()+" minIdx: "+minIdx);
			path.add(n.getValue());
			if(n==goal)
			{
				isGoalFound=true;
				pathAr = new int[path.size()];
				for(int i=0; i<pathAr.length; i++)
				{
					pathAr[i]=path.get(i);
				}
			}
		}
		return pathAr;
	}
	public void generateGradient()
	{
		int origin;
		int right;
		int left;
		int up;
		int down;

		queue.enqueue(goal);
		int distanceFromGoal =0; //heuristics to determine the gradient, in this case simply the manhatten distnace from the goal
		Node<Integer> lastSource = null;
		while(!queue.isEmpty())
		{
			Node<Integer> source = queue.dequeue();
			if(lastSource==null)
			{
				lastSource=source;
			}
			g.addEdge(source,lastSource,distanceFromGoal); //the initial distanceFromGoal is 0 because the distance from the goal to the goal is 0 but this edge won't get added so doesn't matter
			origin = source.getValue();
			right = (origin%g.width<g.width-1)?origin+1:-1; //if origin is the very right, set right index to -1 indicating that there is none
			left = (origin%g.width>0)?origin-1:-1; //if origin%g.width=0 (origin is the very left) then it will become -1 again indicating that ther
			up = (origin<g.width)?-1:origin-g.width; //if origin is smaller than the width, it is at the top row
			down = (origin<g.width*(g.height-1))?origin+g.width:-1; //if origin is smaller than width*(height-1), it is above the very bottom row

			//System.out.println("origin: "+origin+" right: "+right+" left: "+left+" up: "+up+" down: "+down);
			queueing(up);
			queueing(right);
			queueing(down);
			queueing(left);

			lastSource=source;
			distanceFromGoal++; //increase distance from goal each loop step/radial step 
		}
		
	}
	private void queueing(int idx)
	{
		if(idx!=-1 && !isVisited[idx])
		{
			queue.enqueue(g.getNodeSet()[idx]);
			isVisited[idx]=true;
		}
	}

	public void printGrid()
	{
		System.out.println("goal is at: "+g.goalNodeID);
		for(int i=0; i<(g.width*g.height); i++)
		{
			if(i==g.goalNodeID)
			{
				System.out.print("g");
			}
			else
			{
				System.out.print(".");
			}
			if(i%g.width == g.width-1)
			{
				System.out.print("\n");
			}
		}
		System.out.println("---search---");
		Node<Integer> source = g.getNodeSet()[2];
		int[] nodeIdxs = search(source);
		boolean b;
		int count =0;
		for(int i=0; i<(g.width*g.height); i++)
		{
			b=false;
			for(int j=0; j<nodeIdxs.length; j++)
			{
				if(i==nodeIdxs[j])
				{
					b = true;
					count=(count+1)%10;
				}
			}
			if(b)
			{
				System.out.print(count);
			}
			else if(i==g.goalNodeID)
			{
				System.out.print("g");
			}
			else
			{
				System.out.print(".");
			}
			if(i%g.width == g.width-1)
			{
				System.out.print("\n");
			}
		}
	}


	public static void main(String[] args){
		GradientSearcher searcher = new GradientSearcher();
		searcher.generateGradient();
		searcher.printGrid();
	}


}