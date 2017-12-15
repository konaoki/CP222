/**
* Interface for a generic fixed length queue
*/
public class RingQueue<T>{
  int front;
  int back;
  T[] ringqueue;
  int size=0;
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
  public T dequeue()
  {
    T temp = ringqueue[front%ringqueue.length];
    front = (front+1)%ringqueue.length;
    size--;
    return temp;
  }

  /**
  * Enqueues an element at the back of the queue
  * @param v enqueued element
  * @throws OverFlowException if enqueueing a full queue
  */
  public void enqueue(T v)
  {
    ringqueue[back%ringqueue.length]=v;
    back=(back+1)%ringqueue.length;
    size++;
  }
  public boolean isEmpty()
  {
    return (size==0)?true:false;
  }

}
