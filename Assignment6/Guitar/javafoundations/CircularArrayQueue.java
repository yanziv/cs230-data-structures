package javafoundations;

import javafoundations.exceptions.*;

/*
 * Represents an array implementation of the Queue Interface
 * in which neither end of the queue is fixed in the array. 
 * The positions within the array, of the front and rear of 
 * the queue continually change
 * as elements are removed and added to the queue, 
 * cycling back to 0 when they
 * reach the end of the array.
 * */
public class CircularArrayQueue<T> implements Queue<T>
{
    private final int DEFAULT_CAPACITY = 10;
    private int front, rear, count;
    private T[] queue;

    /**
     * Constructor
     * Creates an empty queue.
     */
    public CircularArrayQueue()
    {
        front = rear = count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Adds the specified element to the rear of the queue,
     * expanding the underlying array if needed.
     * 
     * @param The element to be enqueued into the queue
     */
    public void enqueue (T element)
    {
        if (count == queue.length)
            expandCapacity();

        queue[rear] = element;
        rear = (rear+1) % queue.length;
        count++;
    }

    /**
     *  Creates a new array, twice the capacity of the old one,
     *  to store the contents of this queue.
     **/
    private void expandCapacity()
    {
        T[] larger = (T[])(new Object[queue.length*2]);

        for (int index=0; index < count; index++)
            larger[index] = queue[(front+index) % queue.length];

        front = 0;
        rear = count;
        queue = larger;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element removed from the front of the queue.
     * @throws EmptyCollectionException if this queue is empty
     */
     public T dequeue () throws EmptyCollectionException {
        if(count == 0)
            throw new EmptyCollectionException("Dequeue operation failed. The queue is empty.");
      T element = queue[front];
      front = (front + 1) % queue.length;
      count--;
      
      return element;
    }

    /**
     * Returns the element at the front of the queue
     * without removing it.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if this queue is empty
     */
    public T first () throws EmptyCollectionException { 
        if(count == 0)
               throw new EmptyCollectionException("First operation failed. The queue is empty.");
      return queue[front];
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * the number of elements in the queue
     */
    public int size() { 
         return count;
    }

    /**
     * Returns true if the queue contains no elements,
     *  false otherwise.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() { 
        return count == 0;
    }

    /**
     * Returns a string representation of the queue.
     * 
     * @return a string representation of the queue
     */
    public String toString() { 
        String result = "<front of queue>\n";

	   for(int index = front; index < count+front; index++)
         result += queue[index%queue.length] + "\n";
       
       return result;
    }
}