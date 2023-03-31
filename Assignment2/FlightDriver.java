
/**
 * FlightDriver is a driver that tests all methods written in the Flight class
 *
 * @author Veronica Lin yl102
 * @version 9/28/2021
 */

/**
 * Driver class tests all methods in the Flight class
 * contains the main() method 
 */
public class FlightDriver
{
    public static void main(String[] args){
        Flight emirates = new Flight("Emirates", 100, "Saudi Arabia", "Toronto");
        Flight united = new Flight("United", 254, "Singapore", "Boston");
        System.out.println(emirates.getDestination());
        System.out.println(emirates.toString()); 
        System.out.println(Flight.stopOver(emirates, united));
        united.setOrigin("Toronto");
        System.out.println(Flight.stopOver(emirates, united));
        System.out.println(emirates.getAirline());
        emirates.setFlightNumber(511);
        System.out.println(emirates.getFlightNumber());
        united.setOrigin("Los Angeles");
        united.setDestination("Salt Lake City");
        System.out.println(united.getOrigin());
        System.out.println(united.getDestination());
    }
}
