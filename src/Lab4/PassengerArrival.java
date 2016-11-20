package Lab4;

/**
 * Created by shwil on 11/15/2016.
 */
public class PassengerArrival {
    int numPassengers;
    int destinationFloor;
    int timePeriod; //rate of occurance
    int expectedTimeOfArrival;

    public PassengerArrival(int numPassengers, int destinationFloor, int timePeriod, int expectedTimeOfArrival) {
        this.numPassengers = numPassengers;
        this.destinationFloor = destinationFloor;
        this.timePeriod = timePeriod;
        this.expectedTimeOfArrival = expectedTimeOfArrival;
    }
}
