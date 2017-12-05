/**
* Interface for a generic fixed length queue
*/
public class RingQueue<T> implements IQueue<T> {
  int front;
  int back;
  T[] ringqueue;
  public RingQueue(int size)
  {
    front=0;
    back=0;
    ringqueue = (T[])new Object[size];
  }
  /**
  * Dequeues the Front element from the queue
  * @return dequeued element
  * @throws UnderFlowException if dequeueing an empty queue
  */
  public T dequeue() throws UnderFlowException
  {
    if(ringqueue.length==0)throw new UnderFlowException();
    T temp = ringqueue[front%ringqueue.length];
    front = (front+1)%ringqueue.length;
    return temp;
  }

  /**
  * Enqueues an element at the back of the queue
  * @param v enqueued element
  * @throws OverFlowException if enqueueing a full queue
  */
  public void enqueue(T v) throws OverFlowException
  {
    ringqueue[back%ringqueue.length]=v;
    back=(back+1)%ringqueue.length;
  }

}
