
/**
 * HistogramDriver is a driver that tests all methods written in the class Histogram
 *
 * @author Veronica Lin yl102
 * @version 9/28/2021
 */
public class HistogramDriver
{
    /**
     * @param an array of strings
     */
    public static void main(String[] args){
        Histogram.generateIntegers();
        Histogram.allocateFreq();
        Histogram.setMaxdata(10);
        Histogram.setMaxrange(99);
        Histogram.generateHistogram();
        System.out.println();
        Histogram.setMaxdata(555);
        Histogram.setMaxrange(999);
        Histogram.generateHistogram();
    }
}
