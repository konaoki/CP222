public class LinkStack<T> implements IStack<T>
{
  private class Link<T>
  {
    Link<T> next;
    T value;
    public Link(T value, Link<T> next)
    {
      this.value=value;
      this.next=next;
    }

    public Link<T> getNext()
    {
      return next;
    }
    public T getValue()
    {
      return value;
    }
    public String toString()
    {
      return value.toString();
    }

  }
  Link<T> top;
  int size;
  public LinkStack(int a)
  {
    size=0;
    top=new Link<T>(null,null);
  }
  /**
   * Pushes a new value onto the stack
   * @param v the value to push
   */
  public void push(T v) throws OverFlowException
  {
    Link<T> cell = new Link<T>(v,top);
    top=cell;
    size++;
  }

  /**
   * Pops the top value from the stack
   * @return the value
   */
  public T pop() throws UnderFlowException
  {
    if(size==0)throw new UnderFlowException();
    T requestedItem=null;
    Link<T> cell=top.getNext();
    requestedItem=top.getValue();
    top=cell;
    size--;
    return requestedItem;
  }
}
