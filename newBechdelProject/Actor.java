
/**
 * Represents an object of type Actor. An Actor has a name and a gender.
 *
 * @author yl102, vs2, cl103
 * @version 12/5/2021
 */
public class Actor
{
    private String name;
    private String gender;
    
    /**
     * Constructor for Actor. 
     * 
     * @param name The name of the actor
     * @param gender The gender of the actor
     */
    public Actor(String name, String gender){
        this.name = name.replace("\"", "");
        this.gender = gender.replace("\"", "");
    }
    

    /**
     * This method is defined here because Actor (mutable) is used as a key in a Hashtable.
     * It makes sure that same Actors have always the same hash code.
     * So, the hash code of any object taht is used as key in a hash table,
     * has to be produced on an *immutable* quantity,
     * like a String (such a string is the name of the actor in our case)
     * 
     * @return an integer, which is the has code for the name of the actor
     */
    public int hashCode() {
        return name.hashCode();
    }
    
    /**
     * Returns the gender of this actor
     * @return gender of Actor
     */
    public String getGender(){
        return gender;
    }
    
    /**
     * Returns the name of this actor
     * @return name of Actor
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the gender of this actor
     * @param g new gender of actor
     */
    public void setGender(String g){
        gender = g;
    }
    
    /**
     * Sets the name of this actor
     * @param n new name of actor
     */
    public void setName(String n){
        name = n;
    }
    
    /**
     * Retrns a string representation of this Actor.
     * @return s the string representation
     */
    public String toString(){
        String s = ("Name: " + name + "| Gender: " + gender);
        return s;
    }
    
    /**
     * Tests this actor against the input one and determines whether they are equal.
     * Two actors are considered equal if they have the same name and gender.
     * 
     * @return true if both objects are of type Actor, 
     * and have the same name and gender, false in any other case.
     */
    public boolean equals(Object other) {
        if (other instanceof Actor) {
            return this.name.equals(((Actor) other).name) && 
            this.gender.equals(((Actor) other).gender); // Need explicit (Actor) cast to use .name
        } else {
            return false;
        }
    }
    
    /**
     * Main method for testing
     */
    public static void main (String[] args){
        Actor a = new Actor ("Brandon", "Male");
        System.out.println("Testing getName() and getGender() for Brandon, Male.");
        System.out.println(a.getName());
        System.out.println(a.getGender());
        System.out.println();
        
        System.out.println("Testing setName() and setGender(), result should be Kyle, Non-Binary.");
        a.setName("Kyle");
        System.out.println(a.getName());
        a.setGender("Non-binary");
        System.out.println(a.getGender());
        System.out.println();
        
        System.out.println("Testing toString() of Kyle, Non-Binary.");
        System.out.println(a);
        
        System.out.println();
        System.out.println("Creating new Actor: Miya, Female. Testing if Kyle equals to Miya, Should return False");
        Actor b = new Actor ("Miya", "Female");
        System.out.println(a.equals(b));
        
        System.out.println();
        System.out.println("Testing hashCode() on Kyle."); 
        System.out.println(a.hashCode());
    }

}
