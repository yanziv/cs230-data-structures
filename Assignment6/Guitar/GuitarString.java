import javafoundations.*; 
import java.util.Random;
/**
 * The GuitarString class models a vibrating guitar string. 
 *
 * @author yl102
 * @version 11/7/2021
 */
public class GuitarString
{
    //instance variable
    private BoundedQueue<Double> bq; 
    /**
     * Constuctor 
     * Create a guitar string of the given frequency, using a sampling rate of 44,100
     * @param frequency The frequency of the guitar string
     */
    public GuitarString(double frequency){
        int N = (int)Math.ceil((double)44100/frequency); 
        bq = new BoundedQueue<Double>(N); 
        while (!bq.isFull()){
            bq.enqueue(0.0); 
        }
    }
    
    /**
     * Replace the samples in the bounded queue with random values between -0.5 and +0.5
     */
    public void pluck(){
        Random ran = new Random();
        for (int i = 0; i < bq.size(); i ++){
            bq.dequeue(); 
            double randomValue = ran.nextDouble() - 0.5; 
            //System.out.println(randomValue);
            bq.enqueue(randomValue); 
        }   
    }
    
    /**
     * Return the value of the item at the front of the bounded queue
     * @return the value of the item at the front of the bounded queue
     */
    public double sample(){
        return bq.first(); 
    }
    
    /**
     * Apply the Karplus-Strong algorithm (delete the sample at the front of 
     * the bounded queue and add to the end of the bounded queue the average of 
     * the deleted sample and the sample at the front of the bounded queue, 
     * multiplied by the energy decay factor of 0.994
     */
    public void tic(){
        double deleted = bq.dequeue(); 
        double frontValue = sample();
        bq.enqueue(0.994 * 0.5 * (deleted + frontValue));
    }
    
    /**
     * main()
     * for testing 
     */
    public static void main(String[] args){
        GuitarString gStr = new GuitarString(261.63); 
        gStr.pluck(); 
        System.out.println("Testing sample():");
        System.out.println(gStr.sample());
        gStr.tic(); 
    }
    
}

