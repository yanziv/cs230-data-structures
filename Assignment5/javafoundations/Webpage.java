package javafoundations;
import java.util.Scanner; 
import java.io.IOException; 
import java.net.*; 
/**
 * class Webpage implements Comparable<Webpage> and gets html code of different webpages
 * using urls
 *
 * @author sk105, yl102
 * @version 11/01/2021
 */
public class Webpage implements Comparable<Webpage> 
{
    //instance variables
    private String url;
    private int numLines;
    private String html;

    /**
     * Constructor 
     * @param webUrl The url of a webpage as String
     * Construct a Webpage object 
     */
    public Webpage(String webUrl){
        url = webUrl;
    }

    /**
     * getter method
     * @return url Url of a webpage as String
     */
    public String getUrl(){
        return url;
    }

    /**
     * getter method
     * @return numLines The number of lines of html code 
     */
    public int getNumLines(){
        return numLines; 
    }

    /**
     * getter method
     * @return html The HTML code 
     */
    public String getHtml(){
        return html; 
    }

    /**
     * Compare the number of lines of HTML code of two different webpages
     * @param other The other webpage object
     * @return -1, 0, or 1 based on the result of the comparison 
     */
    public int compareTo(Webpage other){
        if (this.numLines < other.numLines){
            return -1; 
        }
        else if (this.numLines == other.numLines){
            return 0;
        }
        return 1; 
    }

    /**
     * Retrieve HTML code from the webpage 
     */
    public void retrieveContent(){
        html = "";
        try{
            URL u1= new URL(url);
            Scanner scan = new Scanner(u1.openStream()); 
            while(scan.hasNext()){
                html += scan.nextLine(); //scan all the lines in the HTML code 
                numLines ++; //get the total number of lines
            }
            scan.close(); 
            html = html.substring(0, 30); 
        }
        catch(IOException ex){
            System.out.println(ex); 
        }
    }

    /**
     * A String representation of the webpage object 
     * Overrides the java toString() method 
     */
    public String toString(){
        String s = url + ": " + numLines + " : " + html + "...";
        return s;
    }

    /**
     * main()
     * for testing 
     */
    public static void main(String[] args){
        Webpage w1 = new Webpage("http://www.google.com");
        Webpage w2 = new Webpage("http://www.bing.com");
        Webpage w3 = new Webpage("https://www.netflix.com"); 

        w1.retrieveContent(); 
        w2.retrieveContent();
        w3.retrieveContent();

        System.out.println("Test compareTo(): comparing w1 to w2");
        System.out.print("Output: ");
        System.out.println(w1.compareTo(w2)); 

        System.out.println(w1.toString()); 
        System.out.println(w2.toString()); 
        System.out.println(w3.toString()); 
    }
}
