import java.util.LinkedList;
import java.io.IOException; 
import java.io.FileNotFoundException; 
import java.io.File; 
import java.util.Scanner;

/**
 * Represents a collection of Movies. 
 * Uses a LinkedList to hold the movie objects. 
 * Movie data (title and test results) are coming from a file named "nextBechdel_allTests.txt". 
 * Data regarding actors who participated in each movie is read from a file named "nextBechdel_castGender.txt". Both files are provided in the "data" folder.
 * 
 * @author yl102, vs2, cl103
 * @version 12/5/2021
 */

public class MovieCollection{
    private LinkedList<Movie> allMovies;
    private LinkedList<Actor> allActors;
    private String testsFileName;
    private String castsFileName;
    private String testResults; 

    /**
     * Constructor for objects of class MovieCollection
     */
    public MovieCollection(String testsFileName, String castsFileName){
        allMovies = new LinkedList<Movie>();
        allActors = new LinkedList<Actor>();
        this.testsFileName = testsFileName;
        this.castsFileName = castsFileName;
        readMovies();
        readCasts(); 
    }

    /**
     * Returns all the movies in a LinkedList
     * 
     * @return Linked List of Movie Objects
     */
    public LinkedList<Movie> getMovies(){
        return allMovies;
    }

    /**
     * Returns the titles of all movies in the collection
     * 
     * @return Linked List of titles of movies in collection 
     */
    public LinkedList<String> getMovieTitles(){
        LinkedList<String> allMovieTitles = new LinkedList<String>();
        for (int i = 0; i<allMovies.size(); i++){
            allMovieTitles.add(allMovies.get(i).getTitle());
        }
        return allMovieTitles;
    }

    /**
     * Returns all the Actors in the collection
     * 
     * @return Linked List of Actor Objects
     */
    public LinkedList<Actor> getActors(){
        return allActors;
    }

    /**
     *     Returns the names of all actors in the collection
     *     
     *     @return Linked List of actor names
     */
    public LinkedList<String> getActorNames(){
        LinkedList<String> allActorNames = new LinkedList<String>();
        for (int i = 0; i<allActors.size(); i++){
            allActorNames.add(allActors.get(i).getName());
        }
        return allActorNames;
    }

    /**
     * Reads the input file, and uses its first column (movie title) to create all movie objects.
     */
    private void readMovies(){
        try{
            Scanner reader = new Scanner(new File(testsFileName));
            reader.nextLine();
            reader.useDelimiter(",|\n");
            //\t|\\r|
            while (reader.hasNext()){
                String title = reader.next();
                //System.out.println(title); 
                if (!allMovies.contains(title)){
                    Movie a = new Movie(title);
                    testResults = ""; 
                    for (int i = 0; i<13; i++){
                        testResults += reader.next() + ",";
                        
                    }
                    //System.out.println();
                    a.setTestResults(testResults);
                    a.addAllActors(castsFileName);
                    allMovies.add(a);
                }
            }
            reader.close();
        }catch(IOException ex){
            System.out.println("File can't be read from. Operation readMovies() failed.");
        }
    }

    /**
     * Reads the casts for each movie, from input casts file; 
     * Assume lines in this file are formatted as followes: 
     * "MOVIE","ACTOR","CHARACTER_NAME","TYPE","BILLING","GENDER" 
     * For example: "Trolls","Ricky Dillon","Aspen Heitz","Supporting","18","Male".
     */
    private void readCasts(){
        try{
            Scanner reader = new Scanner(new File(castsFileName));
            reader.nextLine();
            reader.useDelimiter(",|\n");
            while (reader.hasNext()){
                String title = reader.next().replace("\r","");
                String name = reader.next().replace("\r","");
                String characterName = reader.next().replace("\r","");
                String type = reader.next().replace("\r","");
                String billing = reader.next().replace("\r","");
                String gender = reader.next().replace("\r","");
                Actor a = new Actor(name, gender);
                allActors.add(a);
            }
            reader.close();
        }catch(IOException ex){
            System.out.println("File can't be read from. Operation readCasts() failed.");
        }
    }

    /**
     * Returns a list of all Movies that pass the n-th Bechdel test
     * 
     * @param n The number of the Bechdel test of interest
     * @return A Linked List of all the movies that pass
     */
    public LinkedList<Movie> findAllMoviesPassedTestNum(int n){
        LinkedList<Movie> passedMovies = new LinkedList<Movie>();
        for (int i = 0; i < allMovies.size(); i ++){
            Movie currentMovie = allMovies.get(i); 
            if (currentMovie.getAllTestResults().get(n - 1).equals("0")){
                passedMovies.add(currentMovie); 
            }
        }
        if (passedMovies.size() == 0){
            System.out.println("There's no movies that passed " + n + ".");
        }
        return passedMovies; 
    }

    /**
     * Returns a list of all movies that passed either the x or y Bechdel test
     * 
     * @param x the number of the Bechdel test of interest
     * @param y the other number of the Bechdel test of interest
     * @return A linked list of all the movies that passed either x or y test
     */
    public LinkedList<Movie> findAllMoviesPassedEither(int x, int y){
        LinkedList<Movie> passedMovies = new LinkedList<Movie>();
        for (int i = 0; i < allMovies.size(); i ++){
            Movie currentMovie = allMovies.get(i); 
            if (currentMovie.getAllTestResults().get(x - 1).equals("0") || (currentMovie.getAllTestResults().get(y - 1).equals("0"))){
                passedMovies.add(currentMovie); 
            }
        }
        if (passedMovies.size() == 0){
            System.out.println("There's no movies that passed either " + x + " or " + y + ".");
        }
        return passedMovies;
    }
    
    /**
     * Returns a list of all movies that passed one test but not the other test
     * 
     * @param x the number of the Bechdel test of interest
     * @param y the other number of the Bechdel test that is not passed
     * @return A linked list of all the movies that passed x but not y test
     */
    public LinkedList<Movie> findAllMoviesPassedOneNotOther(int x, int y){
        LinkedList<Movie> passedMovies = new LinkedList<Movie>();
        for (int i = 0; i < allMovies.size(); i ++){
            Movie currentMovie = allMovies.get(i); 
            if (currentMovie.getAllTestResults().get(x - 1).equals("0") && (currentMovie.getAllTestResults().get(y - 1).equals("1"))){
                passedMovies.add(currentMovie); 
            }
        }
        if (passedMovies.size() == 0){
            System.out.println("There's no movies that passed " + x + " and did not pass " + y + ".");
        }
        return passedMovies;
    }

    /**
     * Main Method for testing
     */
    public static void main(String[] args){
        // System.out.println("Testing MovieCollection on \"small_allTests.txt\" and \"small_castGender.txt.\""); 
        // MovieCollection a = new MovieCollection("small_allTests.txt", "small_castGender.txt");

        // System.out.println("Testing getMovies(). Should return LinkedList of Movie Objects.");
        // System.out.println(a.getMovies());

        // System.out.println("Testing getMovieTitles(). Should return LinkedList of Movie Titles.");
        // System.out.println(a.getMovieTitles());
        // System.out.println("Testing getActors(). Should return LinkedList of Actor Objects.");
        // System.out.println(a.getActors());
        // System.out.println("Testing getActorNames(). Should return LinkedList of Actor Names.");
        // System.out.println(a.getActorNames());

        // System.out.println();
        // System.out.println("***EXTENSIVE TESTING***");
        // System.out.println("Find all the movies that passed the Bechdel Test:"); 
        // System.out.println(a.findAllMoviesPassedTestNum(1)); 
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the Pierce or the Landau Test:"); 
        // System.out.println(a.findAllMoviesPassedEither(2, 3));
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the White but not the Rees-Davis Test, should print out the error message."); 
        // System.out.println(a.findAllMoviesPassedOneNotOther(12, 13));
        
        // System.out.println("Find all the movies that passed the Feldman Test, should print out the error message."); 
        // System.out.println(a.findAllMoviesPassedTestNum(4)); 
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the White or the Rees-Davis Test, should print out the error message."); 
        // System.out.println(a.findAllMoviesPassedEither(12, 13));
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the Feldman but not the Villareal Test, should print out the error message."); 
        // System.out.println(a.findAllMoviesPassedOneNotOther(4, 5));
        // System.out.println(); 
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Testing MovieCollection on \"nextBechdel_allTests.txt\" and \"nextBechdel_castGender.txt.\""); 

        MovieCollection b = new MovieCollection("nextBechdel_allTests.txt", "nextBechdel_castGender.txt");

        //System.out.println("Testing getMovies(). Should return LinkedList of Movie Objects.");
        //System.out.println(b.getMovies());

        System.out.println("Testing getMovieTitles(). Should return LinkedList of Movie Titles.");
        System.out.println(b.getMovieTitles());
        System.out.println("Testing getActors(). Should return LinkedList of Actor Objects.");
        System.out.println(b.getActors());
        // System.out.println("Testing getActorNames(). Should return LinkedList of Actor Names.");
        // System.out.println(b.getActorNames());

        // System.out.println();
        // System.out.println("***EXTENSIVE TESTING***");
        // System.out.println("Find all the movies that passed the Bechdel Test."); 
        // System.out.println(b.findAllMoviesPassedTestNum(1)); 
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the Pierce or the Landau Test."); 
        // System.out.println(b.findAllMoviesPassedEither(2, 3));
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the White but not the Rees-Davis Test."); 
        // System.out.println(b.findAllMoviesPassedOneNotOther(12, 13));
        
        // System.out.println("Find all the movies that passed the Feldman Test."); 
        // System.out.println(b.findAllMoviesPassedTestNum(4)); 
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the White or the Rees-Davis Test."); 
        // System.out.println(b.findAllMoviesPassedEither(12, 13));
        
        // System.out.println();
        // System.out.println("Find all the movies that passed the Feldman but not the Villareal Test."); 
        // System.out.println(b.findAllMoviesPassedOneNotOther(4, 5));
    }
}
