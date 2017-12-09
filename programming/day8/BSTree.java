import java.util.Random;
public class BSTree<K extends Comparable<K>, V> implements IDict<K,V>
{
	private class Node<K extends Comparable<K>, V>
	{
		Node<K,V> left;
		Node<K,V> right;
		Node<K,V> parent;
		K key;
		V value;

		public Node(K key, V value, Node<K,V> parent)
		{
			this.key=key;
			this.value=value;
			this.parent=parent;
		}


	}
	Node<K,V> root;
	int size=0;
	IList<Node<K,V>> allNodes = new DoubleLinkList<Node<K,V>>();

	public BSTree()
	{
		root=new Node<K,V>(null,null,null);
	}
	/**
     * Adds a value to the dictionary, replacing the existing value if any.
     * @param k key for the new value
     * @param v value
     * @return the value replaced, otherwise null
     */
	public V add(K k, V v)
	{
		getNode(k).value=v;
		size++;
		return null;

	}

    /**
     * Removes a value and key from the dictionary. An unmatched key should return null.
     * @param k key to remove
     * @return the value of the removed key
     */
    public V remove(K k)
    {
    	boolean isRemoved = false;
    	Node<K,V> wantToRemove=getNode(k);
    	Node<K,V> tracker=wantToRemove;
    	while(!isRemoved)
    	{
    		
    		if(wantToRemove.right==null)
    		{
    			if(wantToRemove.left==null)
    			{
    				if(wantToRemove.parent.left==wantToRemove)
    				{
    					wantToRemove.parent.left=null;
    				}
    				else if(wantToRemove.parent.right==wantToRemove)
    				{
    					wantToRemove.parent.right=null;
    				}
    				isRemoved=true;
    			}
    			else
    			{
    				if(wantToRemove==root)
    				{
    					root=wantToRemove.left;
    					isRemoved=true;
    				}
    				else
    				{
    					wantToRemove.parent.right=wantToRemove.left;
    					isRemoved=true;
    				}
    			}
    		}
    		else
    		{
    			tracker=tracker.right;
    			while(tracker.left!=null)
    			{
    				tracker=tracker.left;
    			}
    			K tempKey = wantToRemove.key;
    			V tempValue = wantToRemove.value;
    			wantToRemove.key=tracker.key;
    			wantToRemove.value=tracker.value;
    			tracker.key=tempKey;
    			tracker.value=tempValue;
    			wantToRemove=tracker;
    		}

    		
    	}
    	size--;
    	return null;
    }
    
    /**
     * Returns the size of the dictionary
     * @return the number of values stored in the dictionary
     */
    public int size()
    {
    	return size;
    }
    
    /**
     * Returns the value associated with a particular key in the dictionary.
     * Returns null if there is no matching key.
     * @param k key to retrieve the value for
     * @return the value
     */
    public V fetch(K k)
    {

    	return getNode(k).value;
    }
    
    /**
     * Returns an array of the keys in the dictionary
     * @return array of all keys
     */
    public K[] keys()
    {
    	allNodes = new DoubleLinkList<Node<K,V>>();
    	setAllNodes(root);
    	K[] keys = (K[])new Comparable[allNodes.size()];
    	for(int i=0; i<allNodes.size(); i++)
    	{
    		keys[i]=allNodes.fetch(i).key;
    	}
    	return keys;
    }
    private void setAllNodes(Node<K,V> node)
    {
    	allNodes.append(node);
    	if(node.left!=null)
    	{
    		setAllNodes(node.left);
    	}
    	if(node.right!=null)
    	{
    		setAllNodes(node.right);
    	}
    }

    private Node<K,V> getNode(K k)
    {
    	Node<K,V> tracker=root;
    	Node<K,V> prevTracker=null;
    	if(root.key==null) //root hasn't been initialized make a new root and return
    	{
    		root=new Node<K,V>(k,null,null);
    		return root;
    	}
    	while(true)
    	{
    		if(k.compareTo(tracker.key)<0)
    		{
    			prevTracker=tracker;
    			tracker=tracker.left;
    			if(tracker==null)
    			{
    				tracker=new Node<K,V>(k,null,prevTracker);
    				prevTracker.left=tracker;
    				return tracker;
    			}
    		}
    		else if(k.compareTo(tracker.key)>0)
    		{
    			prevTracker=tracker;
    			tracker=tracker.right;
    			if(tracker==null)
    			{
    				tracker=new Node<K,V>(k,null,prevTracker);
    				prevTracker.right=tracker;
    				return tracker;
    			}
    		}
    		else
    		{
    			return tracker;
    		}
    	}
    }
    public static void main(String[] args)
    {
    	BSTree<Integer,Integer> tree = new BSTree<Integer,Integer>();
    	tree.add(3,3);
    	tree.add(4,4);
    	tree.add(0,0);
    	tree.add(6,6);
    	tree.add(2,2);
    	tree.add(5,5);
    	tree.add(1,1);
    	for(int i=0; i<7;i++)
    	{
    		System.out.println("i: "+i+" fetched: "+tree.fetch(i));
    	}
    	System.out.println("--------------------");
    	tree.remove(3);
    	tree.remove(2);
    	tree.remove(6);
    	for(int i=0; i<7;i++)
    	{
    		System.out.println("i: "+i+" fetched: "+tree.fetch(i));
    	}
    	System.out.println("--------------------");
    	Integer[] keys = tree.keys();
    	for(int i=0; i<keys.length; i++)
    	{
    		System.out.println("i: "+i+" key: "+keys[i]);
    	}

    }
}