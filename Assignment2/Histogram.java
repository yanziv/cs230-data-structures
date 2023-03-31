
/**
 * Class Histogram allows generation of random numbers and shows how many times
 * each frequency range was selected when random numbers were generated
 *
 * @author Veronica Lin yl102
 * @version 9/28/2021
 */
import java.util.Random;
public class Histogram
{
    //instance variables 
    private static int MAXDATA;
    private static int MAXRANGE;
    
    //setter methods
    /**
     * @param maximum number of data as integer
     */
    public static void setMaxdata(int Maxdata){
        MAXDATA = Maxdata;
    }
    
    /**
     * @param maximum range as integer
     */
    public static void setMaxrange(int Maxrange){
        MAXRANGE = Maxrange; 
    }
    
    /**
     * generates MAXDATA amount of random integers within an array
     * @return an array named data 
     */
    public static int[] generateIntegers(){
        int[] data = new int[MAXDATA];
        int[] frequencyRanges = new int[MAXRANGE];
        Random ran = new Random(); 
        for (int i = 0; i < MAXDATA ; i ++){
            int randomNum = ran.nextInt(MAXRANGE + 1);
            data[i] = randomNum;
            }
        return data; 
    }
    
    /**
     * puts each random number into the right frequency range
     * @return an array named frequencyRanges
     */
    public static int[] allocateFreq(){
        int[] frequencyRanges = new int[MAXRANGE];
        int[] filledArr = generateIntegers();
        for (int j = 0; j < filledArr.length; j ++){
            int rge = filledArr[j]/10;
            frequencyRanges[rge] ++;
        }
        return frequencyRanges;
    }

    /**
     * generates a histogram that shows the number of random numbers there are
     * in each frequency range
     */
    public static void generateHistogram(){
        int freqNum = MAXRANGE/10; 
        int[] freqArr = allocateFreq();
        for (int i = 0; i<= freqNum; i++){
            int signNum = freqArr[i];
            System.out.println(10*i + "-" + (10*i + 9) + " | " + "+".repeat(signNum));
        }
    }
    
    /**
     * test methods
     * @param an array of strings 
     */
    public static void main(String[] args){
        generateIntegers();
        allocateFreq();
        setMaxdata(10);
        setMaxrange(99);
        generateHistogram();
        System.out.println();
        setMaxdata(555);
        setMaxrange(999);
        generateHistogram();
    }
}

    


