import java.util.ArrayList;
public class Dict<K extends Comparable<K>, V> implements IDict<K,V>
{
	private class Item<K extends Comparable<K>, V>
	{
		K key;
		V value;
		public Item(K key, V value)
		{
			this.key=key;
			this.value=value;
		}
	}
	int size=0;
	ArrayList<Item> list = new ArrayList<Item>();
	/**
     * Adds a value to the dictionary, replacing the existing value if any.
     * @param k key for the new value
     * @param v value
     * @return the value replaced, otherwise null
     */
    public V add(K k, V v)
    {
    	Item item = new Item(k,v);
    	list.add(item);
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
    	size--;
    	int i=0;
    	while(i<list.size())
    	{
    		if(list.get(i).key.equals(k))
    		{
    			list.remove(i);
    			return null;
    		}
    		i++;
    	}
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
    	int i=0;
    	while(i<list.size())
    	{
    		if(list.get(i).key.equals(k))
    		{
    			return (V)list.get(i).value;
    		}
    		i++;
    	}
    	return null;
    }
    
    /**
     * Returns an array of the keys in the dictionary
     * @return array of all keys
     */
    public K[] keys()
    {
    	K[] keys = (K[])new Comparable[size];
    	for(int i=0; i<size; i++)
    	{
    		keys[i] = (K)list.get(i).key;
    	}
    	return keys;
    }

    public static void main(String[] args)
    {
    	Dict<Integer,Integer> linear = new Dict<Integer,Integer>();
    	linear.add(3,3);
    	linear.add(4,4);
    	linear.add(0,0);
    	linear.add(6,6);
    	linear.add(2,2);
    	linear.add(5,5);
    	linear.add(1,1);
    	for(int i=0; i<7;i++)
    	{
    		System.out.println("i: "+i+" fetched: "+linear.fetch(i));
    	}
    	System.out.println("--------------------");
    	linear.remove(3);
    	linear.remove(2);
    	linear.remove(6);
    	for(int i=0; i<7;i++)
    	{
    		System.out.println("i: "+i+" fetched: "+linear.fetch(i));
    	}
    	System.out.println("--------------------");
    	Integer[] keys = linear.keys();
    	for(int i=0; i<keys.length; i++)
    	{
    		System.out.println("i: "+i+" key: "+keys[i]);
    	}
    }
}