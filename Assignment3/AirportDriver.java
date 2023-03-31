
/**
 * AirportDriver is a driver of the class Airport
 *
 * @author Veronica Lin
 * @version 10/03/2021
 */
public class AirportDriver
{
    /**
     * calls method from the Airport class 
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
