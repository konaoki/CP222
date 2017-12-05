/**
* Interface for a generic fixed length queue
*/
public class RingQueue<T> implements IQueue<T> {
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
    public void setNext(Link<T> n)
    {
      next=n;
    }
    public String toString()
    {
      return value.toString();
    }

  }
  Link<T> head;
  Link<T> tail;
  int size;
  public RingQueue(int a)
  {
    size=0;
    head=new Link<T>(null,null);
    tail=head;
  }
  /**
  * Dequeues the Front element from the queue
  * @return dequeued element
  * @throws UnderFlowException if dequeueing an empty queue
  */
  public T dequeue() throws UnderFlowException
  {
    if(size==0)throw new UnderFlowException();
    Link<T> tempHead=head;
    head=head.getNext();
    size--;
    return tempHead.getValue();

  }

  /**
  * Enqueues an element at the back of the queue
  * @param v enqueued element
  * @throws OverFlowException if enqueueing a full queue
  */
  public void enqueue(T v) throws OverFlowException
  {
    Link<T> newTail = new Link<T>(v,null);
    tail.setNext(newTail);
    if(size==0)
    {
      head=tail;
    }
    tail=newTail;
    size++;
  }

}
