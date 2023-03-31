import java.util.*;
import java.io.IOException; 
import java.io.FileNotFoundException; 
import java.io.File; 
/**
 * Represents an object of type Movie.
 * A Movie object has a title, some Actors, and results for the twelve Bechdel tests.
 *
 * @author yl102, vs2, cl103
 * @version 12/5/2021
 */
public class Movie
{
    private String title;
    private Hashtable<Actor, String> actorsList;
    private Vector<String> result; 

    /**
     * Constructor
     * Constructs a Movie object with a given title 
     */
    public Movie(String title){
        this.title = title;
        actorsList = new Hashtable<Actor,String>();
        //result = new Vector<String>();
    }

    /**
     * Returns the movie's title
     * 
     * @return title of the movie
     */
    public String getTitle(){
        return title;
    }

    /**
     * populates the testResults vector with "0" and "1"s, 
     * each representing the result of the coresponding test on this movie.
     * 
     * @param results string consisting of of 0's and 1's. Each one of these values denotes the result of the corresponding test on this movie
     */
    public void setTestResults(String results){
        String [] array = results.split("[,]", 0);
        result = new Vector<String>(Arrays.asList(array));
        //System.out.println(result);
    }

    /**
     * returns a Vector with all the Bechdel test results for this movie
     * 
     * @return A Vector with the Bechdel test results for this movie: 
     * A test result can be "1" or "0" indicating that this move passed or did not pass the corresponding test.
     */
    public Vector<String> getAllTestResults(){
        return result;
    }

    /**
     * Reads the input file ("nextBechdel_castGender.txt"), and adds all its Actors to this movie. 
     * Each line in the movie has the following formatting: 
     * Input String has the following formatting: "MOVIE TITLE","ACTOR","CHARACTER_NAME","TYPE","BILLING","GENDER" Example of input: "Trolls","Ricky Dillon","Aspen Heitz","Supporting","18","Male"
     * 
     * @param actorsFile The data file to be read
     */
    public void addAllActors(String actorsFile){
        try{
            Scanner reader = new Scanner(new File(actorsFile)); 
            reader.nextLine();
            reader.useDelimiter(",|\n|\r");
            while (reader.hasNext()){
                if (reader.next().replace("\"","").equals(title)) {
                    //String readerNext = reader.next();
                    //System.out.println("Title: " + title);
                    //System.out.println("Reader.next: " + readerNext);
                    //System.out.println("Does title (plus \"\") equal reader.next(): " + readerNext.equals("\""+title + "\""));
                    String name = reader.next().replace("\"","");
                    //System.out.println(name);
                    reader.next();
                    String roleType = reader.next().replace("\"","");
                    //System.out.println(roleType);
                    reader.next();
                    String gender = reader.next().replace("\"","");
                    //System.out.println(gender);
                    Actor a = new Actor(name, gender);
                    actorsList.put(a,roleType);
                    //System.out.println(actorsList);
                }

            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("File can't be read from. Operation addAllActors() failed.");
        }
    }

    /**
     * Takes in a String, formatted as lines are in the input file ("nextBechdel_castGender.txt"), 
     * generates an Actor, and adds the object to the actors of this movie.\
     * 
     * @param line A string representation of the actor.
     * @return The Actor Object being added
     */
    public Actor addOneActor(String line){
        String [] array = line.split("[,]", 0);
        Actor b = new Actor (array[1], array[5]);
        if (!actorsList.contains(b)){
            actorsList.put(b,array[3].replace("\"", ""));
        }
        else{
            System.out.println("actor is already in the hashtable");
        }
        return b;
    }

    /**
     * Returns the movie's actors in a Hashtable
     * 
     * @return Hashtable of movie's actors
     */
    public Hashtable<Actor, String> getAllActors(){
        return actorsList;
    }

    /**
     *     returns a Linked List with all the actor names who played in this movie.
     *     
     *     @return Linked List containin actor names
     */
    public LinkedList<String> getActors(){
        Enumeration<Actor> actorKeys = actorsList.keys();
        LinkedList<String> actorNames = new LinkedList<String>();
        while (actorKeys.hasMoreElements()){
            Actor currentActor = actorKeys.nextElement();
            actorNames.add(currentActor.getName());
        }
        return actorNames;
    }

    /**
     * Tests this movie object with the input one and determines whether they are equal.
     * 
     * @param other The Movie Object to be compared
     * @return true if both objects are movies and have the same title, 
     * false in any other case.
     */
    public boolean equals(Object other) {
        if (other instanceof Movie) {
            return this.title.equals(((Movie) other).title); // Need explicit (Movie) cast to use .title
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of this movie.
     *     
     * @return a string representation of movie
     */
    public String toString(){
        String s = "The movie title is: " + title + "\nActors in the movie are: \n"+actorsList.toString();
        s+= "\nThe test results for this movie are: " + getAllTestResults() + "\n";
        return s;
    }

    /**
     * Decides if this movie is feminist. 
     * It is considered feminist if it passes the Pierce Test, Feldman Score Test, 
     * Rees Davies Test, and Waithe Test.
     * 
     * @return True if the movie is feminist. False if not. 
     */
    public boolean isFeminist(){
        Vector<String> testResults = getAllTestResults();
        //if passes Pierce Test, Feldman Score Test, Rees Davies Test, and Waithe Test, it isFeminist() = true
        if ((testResults.get(2).equals("0")) && (testResults.get(4).equals("0")) && (testResults.get(9).equals("0")) && (testResults.get(13).equals("0"))){
            return true;
        }
        return false;
    }

    /**
     * Main method for testing
     */
    public static void main (String [] args){
        Movie a = new Movie("Alpha");
        System.out.println("Testing getTitle() method on movie named Alpha, should return Alpha.");
        System.out.println(a.getTitle());

        System.out.println("\nTesting addAllActors() method on new movie named Alpha.");
        a.addAllActors("small_castGender.txt");
        System.out.println(a);

        System.out.println("\nTesting addOneActor() method on new movie named Gamma. Should return \"Cassi Davis, Female\"");
        Movie b = new Movie ("Gamma");
        System.out.println(b.addOneActor("\"Gamma\",\"Cassi Davis\",\"Aunt Bam\",\"Supporting\",\"2\",\"Female\""));
  
        System.out.println("\nTesting getActors() method on movie named Gamma. Should return \"Cassi Davis\"");
        System.out.println(b.getActors());

        System.out.println("\nTesting getAllActors() method on movie named Gamma. Should return \"Cassi Davis\"");
        System.out.println(b.getAllActors());

        System.out.println("\nTesting setTestResults() method. Should return \"Gamma,0,0,0,1,0,0,0,1,0,0,1,1,1\"");
        b.setTestResults("Gamma,0,0,0,1,0,0,0,1,0,0,1,1,1");
        System.out.println(b);

        System.out.println("\nTesting getAllTestResults() method. Should return \"Gamma,0,0,0,1,0,0,0,1,0,0,1,1,1\"");
        System.out.println(b.getAllTestResults());

        System.out.println("\nTesting equals() method on Alpha and Gamma movies. Should return False.");
        System.out.println(a.equals(b));

        a.setTestResults("Alpha,0,1,0,1,0,0,0,0,1,0,0,0,1");
        System.out.println("\nTesting isFeminist() method. Should return true");
        System.out.println(a.isFeminist());

        System.out.println("\nTesting isFeminist() method. Should return false");
        System.out.println(b.isFeminist());

        Movie beta = new Movie("Beta");
        System.out.println("\nTesting addAllActors for Beta");
        beta.addAllActors("small_castGender.txt");
        System.out.println(beta);
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("Testing movie \"Hidden Figures\" from nextBechdel_castGender.txt file.");
        
        Movie c = new Movie("Hidden Figures");
        System.out.println("Testing getTitle() method on movie named Hidden Figures, should return Hidden Figures.");
        System.out.println(c.getTitle());
        
        System.out.println("\nTesting addAllActors() method on new movie named Hidden Figures.");
        c.addAllActors("nextBechdel_castGender.txt");
        System.out.println(c);
        
        System.out.println("\nTesting getActors() method on movie named Hidden Figures. Should return LinkedList of actors names.");
        System.out.println(c.getActors());

        System.out.println("\nTesting getAllActors() method on movie named Hidden Figures. Should return HashTable of actor objects");
        System.out.println(c.getAllActors());
        
        System.out.println("\nTesting isFeminist() method on Hidden Figures. Should return false");
        System.out.println(b.isFeminist());
    }

}
