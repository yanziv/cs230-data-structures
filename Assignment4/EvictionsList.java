import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * EvictionsList.java
 * Reads some Town data from a file.
 * Determines which of the towns have increased eviction rate, according to a threshold
 * Outputs the results in a txt file.
 * This version uses java's LinkedLists
 *
 * @author cs230 staff (SK) and ****your names here
 * @version 9/20
 */
public class EvictionsList
{
    //instance variables
    private String inFileName; //input data
    private LinkedList<Town> towns;

    private int numFlagged; //num of flagged towns
    //Notice, this number changes when the theshold changes

    private double THRESHOLD = 0.30; //0.30% eviction rate threshold;

    /**
     *  sets the new threshold
     *
     * @param t the new treshhold
     * */
    public void setThreshold(double t){
        THRESHOLD = t;
    }

    /**
     * Returns a String representation of this object: The names of the
     * files the program reads from and writes to, the eviction rate threshold,
     * and the flagged towns.
     *
     * @return A string representation of the object
     * */
    public String toString() {
        String s = "Read " + towns.size() + " towns  from " + inFileName +
            ".\n The following (" + numFlagged + ")  are flagged, at threshold: "+ THRESHOLD + ":\n";

        s = s + "popul \tpovRate" + " \tevictions "+ "\teviction rate  \t   on watch \tname, state \n" ;
        for (int i=0; i<towns.size(); i++) {
            Town currentTown = towns.get(i);
            if (currentTown.getFlagged())
                s = s + currentTown + "\n";
        }
        return s;
    }



    /**
     * main()
     * for testing
     *
     */
    public static void main (String[] args) {
        String inFileName = "evictionData.txt";
        Evictions e = new Evictions(inFileName);
        e.readFromFile(inFileName);

        e.setThreshold(0.08); //0.08%
        e.flagTowns();
        e.writeToFile("results1.txt");
        System.out.println(e);

    }
}
