/**
* The interface for the link cells in a singlely linked list
*/
public class SingleLinkList<T> implements IList<T> {
  private class SingleLinkListCell<T> implements ISLink<T>
  {
    T value;
    ISLink<T> next;
    public SingleLinkListCell(T value, ISLink<T> next)
    {
      this.value = value;
      this.next = next;
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
    public ISLink<T> getNext()
    {
      return this.next;
    }

    public String toString()
    {
      return value.toString();
    }
    /**
    * Sets the next cell in the list
    * @param c the next cell
    */
    public void setNext(ISLink<T> c)
    {
      this.next=c;
    }
  }

  ISLink<T> head;
  ISLink<T> tail;
  ISLink<T> current;
  int size;

  public SingleLinkList()
  {
    current=new SingleLinkListCell<T>(null,null);
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
    ISLink cell = head;
    //System.out.println("idx: "+idx+" size: "+size);
    if(idx<size-1)
    {
      if(idx>0)
      {
        while(count<=idx-1)
        {
          if(count==idx-1)
          {
            cell.setNext(new SingleLinkListCell(v, cell.getNext()));
          }
          count++;
          cell=cell.getNext();
        }
      }
      else
      {
        cell = new SingleLinkListCell(v,head);
        head = cell;
      }
    }
    else if(idx==size-1)
    {
      while(count<=idx-1)
      {
        if(count==idx-1)
        {
          cell.setNext(new SingleLinkListCell(v, cell.getNext()));
        }
        count++;
        cell=cell.getNext();
      }
    }
    else if(idx==size)
    {
      cell = new SingleLinkListCell(v,null);
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
    ISLink<T> cell = new SingleLinkListCell<T>(v, null);
    jumpToTail();
    current.setNext(cell);
    tail=cell;
    //System.out.println("Tail: "+tail);
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
    ISLink cellC = head;
    while(cellC!=current)
    {
      countC++;
      cellC=cellC.getNext();
    }
    //System.out.println("countC: "+countC+" cellC: "+cellC.getValue());
    int count=0;
    ISLink cell = head;
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
    ISLink cell = head;
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
      ISLink<T> tempS=head;
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
      ISLink cell = head;
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
    //System.out.println("current next: "+current.getNext());
    current=current.getNext();
  }

  /**
  * Advances the <i>current</i> index to the previous index, if possible.
  */
  public void prev()
  {
    int currentIndex=0;
    int count=0;
    ISLink<T> cell = head;
    while(!cell.equals(current))
    {
      //System.out.println("cell was before current");
      cell=cell.getNext();
      currentIndex++;
    }
    jumpToHead();
    while(count<currentIndex-1)
    {
      //System.out.println("next should be called");
      next();
      count++;
    }
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

  public static void main(String[] args)
  {
    IList<Integer> l = new SingleLinkList<Integer>();
    l.append(0);
    l.append(1);
    l.append(2);
    l.append(3);
    l.append(4);
    //l.jumpToHead();
    //l.remove();
    l.jumpToTail();
    l.remove();
    System.out.println(l.fetch(0));
    System.out.println(l.fetch(1));
    System.out.println(l.fetch(2));
    System.out.println(l.fetch(3));
    //System.out.println(l.fetch(4));
  }

}
