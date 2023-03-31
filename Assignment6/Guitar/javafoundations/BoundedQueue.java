package javafoundations;
//import javafoundations.CircularArrayQueue;
/**
 * The BoundedQueue class inherits the Queue class and adds a maximum capacity to the queue. 
 *
 * @author yl102
 * @version 11/06/2021
 */
public class BoundedQueue<T> extends CircularArrayQueue<T>
{
    //instance variable
    private int capacity; //capacity of the bounded queue
    /**  
     * Constructor
     * Create a BoundedQueue object with a max capacity 
     * @param capacity The capacity of the bounded queue as Integer 
     */
    public BoundedQueue(int capacity){
        super(); 
        this.capacity = capacity; 
    }
    
    /**
     * Check whether the bounded queue is full
     * @return a boolean value that specifies whether the bounded queue is full 
     */
    public boolean isFull(){
        if (size() >= capacity){
            return true;
        }
        return false; 
    }
    
    /**
     * Enqueue an element to the bounded queue if the queue is not full
     * @param element An element of a generic type 
     */
    public void enqueue(T element){
        if (!isFull()){
            super.enqueue(element); 
        }
        else{
            System.out.println("Enqueue failed. BoundedQueue is full.");
        }
    }
    
    /**
     * main()
     * for testing
     */
    public static void main(String[] args){
        BoundedQueue<Integer> bq1 = new BoundedQueue<Integer>(5); 
        System.out.println("Testing enqueue():"); 
        bq1.enqueue(5);
        bq1.enqueue(1);
        bq1.enqueue(6);
        bq1.enqueue(7);
        bq1.enqueue(9);
        System.out.println(bq1.toString()); 
        //BoundedQueue bq1 is full now
        System.out.println("----------------------------------------------------------");
        System.out.println("Testing isFull():");
        System.out.println("BoundedQueue bq1 is full, expected result: true");
        System.out.print("Actual output: ");
        System.out.println(bq1.isFull()); 
        System.out.println("----------------------------------------------------------");
        System.out.println("Testing adding two more elements to the BoundedQueue when it's already full:");
        System.out.println("(Expect enqueue failed)");
        System.out.println("Actual Output:");
        bq1.enqueue(2); //enqueue failed, message gets printed out
        bq1.enqueue(3); //enqueue failed, message gets printed out
    }
}
