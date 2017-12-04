/**
* List interface based on the 11/29 discussion of what a program using lists might
* need to do. This will be our definition for the list ADT this block.
*/
public class ArrayList<T> implements IList<T>{
  int size;
  T[] backingArray;
  int current;
  public ArrayList()
  {
    size=0;
    current=0;
    backingArray=(T[])new Object[0];
  }
  /**
  * Inserts an item at a specific index in the list
  * @param idx where the item should be inserted
  * @param v the value to insert
  */
  public void insert(int idx, T v)
  {
    T[] tempArray=(T[])new Object[backingArray.length+1];
    tempArray[idx]=v;
    for(int i=0; i<backingArray.length; i++)
    {
      if(i<idx)tempArray[i]=backingArray[i];
      else tempArray[i+1]=backingArray[i];
    }
    backingArray=tempArray;
    size++;
  }

  /**
  * Adds an item to the end of list. Called 'Add' in class, but more usually called
  * append in other libraries. Moves <i>current</i> to the end of the list.
  * @param v Item to add
  */
  public void append(T v)
  {
    T[] tempArray=(T[])new Object[backingArray.length+1];
    tempArray[tempArray.length-1]=v;
    for(int i=0; i<backingArray.length; i++)
    {
      tempArray[i]=backingArray[i];
    }
    backingArray=tempArray;
    size++;
    current=size-1;
  }

  /**
  * Removes the item at the <i>current</i> index in the list. <i>Current</i> becomes
  * the previous item in the list, if such element exists.
  */
  public void remove()
  {
    if(backingArray.length>0)
    {
      T[] tempArray=(T[])new Object[backingArray.length-1];
      for(int i=0; i<tempArray.length; i++)
      {
        tempArray[i]=(i<current)?backingArray[i]:backingArray[i+1];
      }
      backingArray=tempArray;
      size--;
      if(current>0)current--;
    }
  }

  /**
  * Removes the item at a specific index
  * @param idx index of item to remove
  */
  public void remove(int idx)
  {
    if(backingArray.length>0)
    {
      T[] tempArray=(T[])new Object[backingArray.length-1];
      for(int i=0; i<tempArray.length; i++)
      {
        tempArray[i]=(i<idx)?backingArray[i]:backingArray[i+1];
      }
      backingArray=tempArray;
      size--;
    }
  }

  /**
  * Changes the location of an existing element in the list
  * @param sidx - The initial index for the element to move
  * @param didx - The final index for the element to move
  */
  public void move(int sidx, int didx)
  {
    if(didx>sidx)
    {
      T tempValue = backingArray[sidx];
      for(int i=0; i<backingArray.length; i++)
      {
        if(i>sidx && i<=didx)backingArray[i-1]=backingArray[i];
      }
      backingArray[didx]=tempValue;
    }
    else if(sidx>didx)
    {
      T tempValue = backingArray[sidx];
      for(int i=backingArray.length-1; i>=0; i--)
      {
        if(i>didx && i<=sidx)backingArray[i]=backingArray[i-1];
      }
      backingArray[didx]=tempValue;
    }
  }

  /**
  * Fetches the value at the <i>current</i> index in the list.
  * @return the requested item
  */
  public T fetch()
  {
    return backingArray[current];
  }

  /**
  * Fetches the value at a specific index in the list.
  * @param idx index of the item to return
  * @return the requested item
  */
  public T fetch(int idx)
  {
    return backingArray[idx];
  }

  /**
  * Advances the <i>current</i> index to the next index, if possible.
  */
  public void next()
  {
    if(current<size-1)current++;
  }

  /**
  * Advances the <i>current</i> index to the previous index, if possible.
  */
  public void prev()
  {
    if(current>0)current--;
  }

  /**
  * Advances the <i>current</i> to the tail element
  */
  public void jumpToTail()
  {
    current=backingArray.length-1;
  }

  /**
  * Advances the <i>current</i> to the head element
  */
  public void jumpToHead()
  {
    current=0;
  }

  /**
  * Returns the number of elements in the list
  */
  public int size()
  {
    return size;
  }
}
