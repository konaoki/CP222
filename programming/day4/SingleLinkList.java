/**
* The interface for the link cells in a singlely linked list
*/
public class SingleLinkList<T> implements IList<T> {
  T value;
  ISLink<T> next;

  public SingleLinkList()
  {

  }

  class SingleLinkListCell<T> implements ISLink<T>
  {
    /**
    * Gets the current value for this link cell
    * @return the value
    */
    public T getValue()
    {
      return null;
    }

    /**
    * Sets the current value for this link cell
    * @param v the value to place in this cell
    */
    public void setValue(T v)
    {

    }

    /**
    * Gets the next cell in the list
    * @return the cell
    */
    public ISLink<T> getNext()
    {
      return null;
    }

    /**
    * Sets the next cell in the list
    * @param c the next cell
    */
    public void setNext(ISLink<T> c)
    {

    }
  }


  /**
   * Inserts an item at a specific index in the list
   * @param idx where the item should be inserted
   * @param v the value to insert
   */
  public void insert(int idx, T v)
  {

  }

/**
 * Adds an item to the end of list. Called 'Add' in class, but more usually called
 * append in other libraries. Moves <i>current</i> to the end of the list.
 * @param v Item to add
 */
public void append(T v)
{

}

/**
 * Removes the item at the <i>current</i> index in the list. <i>Current</i> becomes
 * the previous item in the list, if such element exists.
 */
public void remove()
{

}

/**
 * Removes the item at a specific index
 * @param idx index of item to remove
 */
public void remove(int idx)
{

}

/**
 * Changes the location of an existing element in the list
 * @param sidx - The initial index for the element to move
 * @param didx - The final index for the element to move
 */
	/**
	 * Changes the location of an existing element in the list
	 * @param sidx - The initial index for the element to move
	 * @param didx - The final index for the element to move
	 */
	public void move(int sidx, int didx)
  {

  }

	/**
	 * Fetches the value at the <i>current</i> index in the list.
	 * @return the requested item
	 */
	public T fetch()
  {
    return null;
  }

	/**
	 * Fetches the value at a specific index in the list.
	 * @param idx index of the item to return
	 * @return the requested item
	 */
	public T fetch(int idx)
  {
    return null;
  }

	/**
	 * Advances the <i>current</i> index to the next index, if possible.
	 */
	public void next()
  {

  }

	/**
	 * Advances the <i>current</i> index to the previous index, if possible.
	 */
	public void prev()
  {

  }

	/**
	 * Advances the <i>current</i> to the tail element
	 */
	public void jumpToTail()
  {

  }

	/**
	 * Advances the <i>current</i> to the head element
	 */
	public void jumpToHead()
  {

  }

	/**
	 * Returns the number of elements in the list
	 */
	public int size()
  {
    return 0;
  }

}
