/**
* List interface based on the 11/29 discussion of what a program using lists might
* need to do. This will be our definition for the list ADT this block.
*/
public class DoubleLinkList<T> implements IList<T> {
  /**
  * The interface for the link cells in a doubly linked list
  */
  private class DoubleLinkListCell<T> implements IDLink<T> {
    T value;
    IDLink<T> next;
    IDLink<T> previous;
    public DoubleLinkListCell(T value, IDLink<T> next, IDLink<T> previous)
    {
      this.value = value;
      this.next = next;
      this.previous = previous;
    }
    /**
    * Gets the current value for this link cell
    * @return the value
    */
    public T getValue()
    {
      return value;
    }

    /**
    * Sets the current value for this link cell
    * @param v the value to place in this cell
    */
    public void setValue(T v)
    {
      value=v;
    }

    /**
    * Gets the next cell in the list
    * @return the cell
    */
    public IDLink<T> getNext()
    {
      return this.next;
    }

    /**
    * Gets the previous cell in the list
    * @return the cell
    */
    public IDLink<T> getPrev()
    {
      return this.previous;
    }

    /**
    * Sets the next cell in the list
    * @param c the next cell
    */
    public void setNext(IDLink<T> c)
    {
      this.next=c;
    }

    /**
    * Sets the next cell in the list
    * @param c the next cell
    */
    public void setPrev(IDLink<T> c)
    {
      this.previous=c;
    }
    public String toString()
    {
      return value.toString();
    }
  }
  IDLink<T> head;
  IDLink<T> tail;
  IDLink<T> current;
  int size;
  public DoubleLinkList()
  {
    current=new DoubleLinkListCell<T>(null,null,null);
    head=current;
    tail=current;
    size=0;
  }
  /**
  * Inserts an item at a specific index in the list
  * @param idx where the item should be inserted
  * @param v the value to insert
  */
  public void insert(int idx, T v)
  {
    int count=0;
    IDLink cell = head;
    //System.out.println("idx: "+idx+" size: "+size);
    if(idx<size-1)
    {
      if(idx>0)
      {
        while(count<=idx-1)
        {
          if(count==idx-1)
          {
            cell.setNext(new DoubleLinkListCell(v, cell.getNext(),null));
          }
          count++;
          cell=cell.getNext();
        }
      }
      else
      {
        cell = new DoubleLinkListCell(v,head,null);
        head = cell;
      }
    }
    else if(idx==size-1)
    {
      while(count<=idx-1)
      {
        if(count==idx-1)
        {
          cell.setNext(new DoubleLinkListCell(v, cell.getNext(),null));
        }
        count++;
        cell=cell.getNext();
      }
    }
    else if(idx==size)
    {
      cell = new DoubleLinkListCell(v,null,null);
      tail.setNext(cell);
      tail=cell;
    }
    size++;

  }

  /**
  * Adds an item to the end of list. Called 'Add' in class, but more usually called
  * append in other libraries. Moves <i>current</i> to the end of the list.
  * @param v Item to add
  */
  public void append(T v)
  {
    IDLink<T> cell = new DoubleLinkListCell<T>(v, null,null);
    jumpToTail();
    current.setNext(cell);
    tail=cell;
    cell.setPrev(current);
    jumpToTail();
    if(size==0)
    {
      head=tail;
    }
    size++;
  }

  /**
  * Removes the item at the <i>current</i> index in the list. <i>Current</i> becomes
  * the previous item in the list, if such element exists.
  */
  public void remove()
  {
    int countC=0;
    IDLink cellC = head;
    while(cellC!=current)
    {
      countC++;
      cellC=cellC.getNext();
    }
    //System.out.println("countC: "+countC+" cellC: "+cellC.getValue());
    int count=0;
    IDLink cell = head;
    if(countC<size && countC>0 && size>0)
    {
      while(count<=countC-1)
      {
        if(count==countC-1)
        {
          if(cell.getNext().equals(tail))
          {
            tail=cell;
            current=tail;
          }
          cell.setNext(cell.getNext().getNext());
          current=cell;
        }
        cell=cell.getNext();
        count++;
      }
    }
    else if(countC==0)
    {
      next();
      head=head.getNext();
    }
    size--;

  }

  /**
  * Removes the item at a specific index
  * @param idx index of item to remove
  */
  public void remove(int idx)
  {
    int count=0;
    IDLink cell = head;
    if(idx<size && idx>0 && size>0)
    {
      while(count<=idx-1)
      {
        if(count==idx-1)
        {
          if(cell.getNext().equals(tail))
          {
            tail=cell;
          }
          cell.setNext(cell.getNext().getNext());
        }
        cell=cell.getNext();
        count++;
      }
    }
    else if(idx==0)
    {
      if(current.equals(head))
      {
        next();
      }
      head=head.getNext();
    }
    size--;

  }

  /**
  * Changes the location of an existing element in the list
  * @param sidx - The initial index for the element to move
  * @param didx - The final index for the element to move
  */
  public void move(int sidx, int didx)
  {
    if(sidx!=didx)
    {
      int count = 0;
      IDLink<T> tempS=head;
      while(count<sidx)
      {
        count++;
        tempS=tempS.getNext();
      }
      remove(sidx);
      insert(didx,tempS.getValue());
    }
  }

  /**
  * Fetches the value at the <i>current</i> index in the list.
  * @return the requested item
  */
  public T fetch()
  {
    return current.getValue();
  }

  /**
  * Fetches the value at a specific index in the list.
  * @param idx index of the item to return
  * @return the requested item
  */
  public T fetch(int idx)
  {
    if(idx<size && size>0)
    {
      int count=0;
      IDLink cell = head;
      while(count<=idx)
      {
        if(count==idx)
        {
          return (T)cell.getValue();
        }
        cell=cell.getNext();
        count++;
      }
    }
    return null;
  }

  /**
  * Advances the <i>current</i> index to the next index, if possible.
  */
  public void next()
  {
    current=current.getNext();
  }

  /**
  * Advances the <i>current</i> index to the previous index, if possible.
  */
  public void prev()
  {
    current=current.getPrev();
  }

  /**
  * Advances the <i>current</i> to the tail element
  */
  public void jumpToTail()
  {
    current=tail;
  }

  /**
  * Advances the <i>current</i> to the head element
  */
  public void jumpToHead()
  {
    current=head;
  }

  /**
  * Returns the number of elements in the list
  */
  public int size()
  {
    return size;
  }
}
