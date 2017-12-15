import java.util.ArrayList;
public class GradientSearcher{
	GraphReader r;
	Graph<Integer,Integer> g;
	Boolean[] isVisited;
	RingQueue<Node<Integer>> queue;
	Node<Integer> goal;
	Node<Integer> currentNode;
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

		System.out.println("width: "+g.width +" height: "+g.height);
		System.out.println("w*h: "+g.width*g.height+" nodes num: "+g.getNodeSet().length);
		for(int i=0; i<(g.width*g.height); i++)
		{
			if(i==g.goalNodeID)
			{
				System.out.print("g");
			}
			else if(g.getNodeSet()[i].material == "vacuum")
			{
				System.out.print(".");
			}
			else
			{
				System.out.print("#");
			}
			if(i%g.width == g.width-1)
			{
				System.out.print("\n");
			}
		}
	}
	public int[] search(Node<Integer> n)
	{
		boolean isGoalFound=false;
		Edge<Integer,Integer>[] edgesFrom;
		ArrayList<Integer> path = new ArrayList<Integer>();
		int[] pathAr = new int[0];
		while(!isGoalFound)
		{
			//edgesFrom = g.getEdgesFrom(n);
			edgesFrom = n.getEdgeSet();
			int minIdx=0;
			for(int i=0; i<edgesFrom.length; i++)
			{
				if(edgesFrom[i].getWeight() <  edgesFrom[minIdx].getWeight())
				{
					minIdx=i;
				}
			}
			
			n=edgesFrom[minIdx].getDestination();
			//System.out.println("to node: "+n.getValue()+" edge weight: "+edgesFrom[minIdx].getWeight()+" minIdx: "+minIdx);
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
	public void searchWithIndex(int idx)
	{
		Node<Integer> source = g.getNodeSet()[idx];
		if(source.material == "vacuum")
		{
			search(source);
		}
	}
	public void generateGradient()
	{
		int origin;
		int right;
		int left;
		int up;
		int down;
		int radialStep=0;//heuristics to determine the gradient, in this case simply the manhatten distnace from the goal
		int naiveDistace=0;

		queue.enqueue(goal);
		while(!queue.isEmpty())
		{
			currentNode = queue.dequeue();
			g.addEdge(currentNode,currentNode.parentNode,naiveDistace); //the initial distanceFromGoal is 0 because the distance from the goal to the goal is 0 but this edge won't get added so doesn't matter
			origin = currentNode.getValue();
			right = (origin%g.width<g.width-2)?origin+1:-1; //if origin is the very right, set right index to -1 indicating that there is none
			left = (origin%g.width>1)?origin-1:-1; //if origin%g.width=0 (origin is the very left) then it will become -1 again indicating that ther
			up = (origin<g.width*2)?-1:origin-g.width; //if origin is smaller than the width, it is at the top row
			down = (origin<g.width*(g.height-1)-g.width)?origin+g.width:-1; //if origin is smaller than width*(height-1), it is above the very bottom row

			//System.out.println("origin: "+origin+" right: "+right+" left: "+left+" up: "+up+" down: "+down);
			queueing(up,currentNode);
			queueing(right,currentNode);
			queueing(down,currentNode);
			queueing(left,currentNode);

			naiveDistace++;
			if((int)((Math.sqrt(1+2*g.getEdgeSet().length)-1)/2) > radialStep)
			{
				radialStep++; //doesn't work with walls
			}
			
		}
		
	}
	private void queueing(int idx, Node<Integer> curr)
	{
		if(idx!=-1 && !isVisited[idx])
		{
			Node<Integer> n = g.getNodeSet()[idx];
			if(n.material=="vacuum")
			{
				n.parentNode = curr;
				queue.enqueue(n);
				isVisited[idx]=true;
			}
		}
	}

	public void printSearchedGrid(int idx)
	{
		System.out.println("---search from "+idx+"---");
		Node<Integer> source = g.getNodeSet()[idx];
		if(source.material == "vacuum")
		{
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
				if(i==idx)
				{
					System.out.print("s");
				}
				else if(i==g.goalNodeID)
				{
					System.out.print("g");
				}
				else if(b)
				{
					System.out.print("p");
				}				
				else if(g.getNodeSet()[i].material == "vacuum")
				{
					System.out.print(".");
				}
				else
				{
					System.out.print("#");
				}
				if(i%g.width == g.width-1)
				{
					System.out.print("\n");
				}
			}
		}
		
	}





}