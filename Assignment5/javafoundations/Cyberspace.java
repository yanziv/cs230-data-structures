package javafoundations;
import javafoundations.exceptions.*; 
/**
 * Write a description of class Cyberspace here.
 *
 * @author sk105, yl102
 * @version 11/01/2021
 */
public class Cyberspace 
{
    //instance variable
    private ArrayStack<Webpage> webStack; // webpage ArrayStack
    /**
     * Constructor
     * Construct a Cyberspace object 
     */
    public Cyberspace(){
        webStack = new ArrayStack<Webpage>(); 
    }

    /**
     * Add a new website to the 
     */
    public void add(Webpage newSite){
        webStack.push(newSite); 
    }

    public Webpage findLargest(){
        Webpage largest = null;
        Webpage w; 
        ArrayStack<Webpage> temp = new ArrayStack<Webpage>();
        try{
            largest = webStack.pop();
            temp.push(largest); 
            while(!webStack.isEmpty()){
                w = webStack.pop(); 
                temp.push(w); 
                if (largest.compareTo(w) == -1) //when larger is smaller than temp
                {
                    largest = w; 
                }
            }
            
        }
        catch(EmptyCollectionException e){
            System.out.println(e); 
        }
        return largest; 
    }

    public void printWebStack(){
        System.out.println("Results from visiting " + webStack.size() + " pages (LIFO order):\n" 
        + webStack.toString());
    }

    public static void main(String[] args){
        Cyberspace c1 = new Cyberspace();
        Webpage w1 = new Webpage("http://www.google.com");
        Webpage w2 = new Webpage("http://www.bing.com");
        Webpage w3 = new Webpage("https://www.netflix.com"); 
        c1.add(w1);
        c1.add(w2);
        c1.add(w3);
        System.out.println("Test printWebStack():");
        c1.printWebStack();
        System.out.println("Test findLargest():");
        System.out.println(c1.findLargest());
    }
}
