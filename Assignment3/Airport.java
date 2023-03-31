
/**
 * class Airport keeps track of the flights that use a certain airport
 *
 * @author Veronica Lin yl102
 * @version 10/03/2021
 */
public class Airport
{
    private String name;
    private Flight[] flightsArr; //stores flights that use this airport (as origin + destination)
    private int flightsNum = 0; //assumes that no flight uses the airport in the beginning

    /**
     * Constructor for objects of class Airport
     * @param name The name of the airport
     */
    public Airport(String name){
        this.name = name;
        flightsArr = new Flight[10]; //set the length of the array to 10
    }

    /**
     * @param f A Flight object
     * takes a Flight as input and adds it to the array of Flights
     */
    public void addFlight(Flight f){
        if (flightsNum < flightsArr.length){
            flightsArr[flightsNum] = f;
            flightsNum ++;
        }
        else{
            System.out.println("The airport has reached its full capacity and cannot accomodate any more flights.");
        }
    }

    /**
     * Returns a String representation of this airport
     * Overrides the toString in class java.lang.Object
     * @return a String representation of this airport, containing its capacity,
     * the number of flights it currently serves, as well as those flights.
     */
    public String toString(){
        int capacity = flightsArr.length;
        String flightInfo = " ";
        for (int i = 0; i < flightsNum; i++){
            Flight f = flightsArr[i];
            flightInfo += f.toString() + ". "; 
        }
        return "The " + name + " airport's capacity is " + capacity + " and currently serves " + flightsNum + " number of flights." + flightInfo;
    }

    /**
     * Takes in the name of an airline, and returns all the flights of this 
     * airline that use this airport
     * @param inputAirline The name of the airline of interest
     * @return an array containing all flights of the given airline that use this airport
     */
    public Flight[] findFlightsByAirline(String inputAirline){
        Flight [] sameAirline = new Flight[10];
        int index = 0;
        for (int i =0; i < flightsNum; i++){
            if (flightsArr[i].getAirline() == inputAirline){
                sameAirline[index] = flightsArr[i];
                index ++; 
            }
        }
        return sameAirline; 
    }

    /**
     * takes in the name of an airline, and prints all its flights 
     * that use this airport
     * @param inputAirline The name of the airline of interest 
     */
    public void printFlightsByAirline(String inputAirline){
        Flight [] printArr = findFlightsByAirline(inputAirline);
        String printStr = "";
        for (int i = 0; i < printArr.length; i ++){
            if (printArr[i] != null){
                printStr += printArr[i].toString() + ". ";
            }
        }
        System.out.println(printStr);
    }

    /**
     * main() method for testing
     */
    public static void main(String[] args){
        //create three Flight objects 
        Flight emirates = new Flight("Emirates", 100, "Saudi Arabia", "Toronto");
        Flight united1 = new Flight("United", 254, "Singapore", "Boston");
        Flight united2 = new Flight("United", 252, "Hong Kong", "Serbia");
        
        //create an Airport object 
        Airport airport1 = new Airport("Tokyo International");
        //add Flight objects to the Airport array
        airport1.addFlight(emirates);
        airport1.addFlight(united1);
        airport1.addFlight(united2);
        
        //Test toString() method
        System.out.println("Expected output for airport1.toString(): The Tokyo International airport's capacity is 10 and currently serves 3 number of flights. There is a flight that is from the airline Emirates and has the flight number 100 and is going from Saudi Arabia to Toronto. There is a flight that is from the airline United and has the flight number 254 and is going from Singapore to Boston. There is a flight that is from the airline United and has the flight number 252 and is going from Hong Kong to Serbia.");
        System.out.println("Actual Output:");
        System.out.println(airport1.toString());
        System.out.println("------------------------------");
        
        //Normal Case
        System.out.println("Normal Case Testing:");
        //airport1.findFlightsByAirline("United");
        System.out.println("printFlightsByAirline(\"United\") expected output: There is a flight that is from the airline United and has the flight number 254 and is going from Singapore to Boston. There is a flight that is from the airline United and has the flight number 252 and is going from Hong Kong to Serbia.");
        System.out.println("printFlightsByAirline(\"United\") Actual Output:");
        airport1.printFlightsByAirline("United"); 
        System.out.println("------------------------------");
        
        //edge case
        System.out.println("Edge Case Testing: No flight is added to the airport");
        Airport airport2 = new Airport("Logan International");
        System.out.println("airport2.toString() expected output: The Logan International airport's capacity is 10 and currently serves 0 number of flights.");
        System.out.println("Actual Output:");
        System.out.println(airport2.toString());
        
        System.out.println("Expected output for printFlightsByAirline(\"United\"): nothing gets printed out.");
        System.out.println("Actual Output:");
        airport2.findFlightsByAirline("United");
        airport2.printFlightsByAirline("United");
        
    }
}
