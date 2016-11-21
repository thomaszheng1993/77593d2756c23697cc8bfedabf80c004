package Lab4;

public class BuildingFloor {
    private int[] totalDestinationRequests;
    private int[] arrivedPassengers;
    private int[] passengerRequests;
    private int approachingElevator;

    public BuildingFloor() {
        this.totalDestinationRequests = new int[5];
        this.arrivedPassengers = new int[5];
        this.passengerRequests = new int[5];
        this.approachingElevator = -1;
    }

    public int getTotalDestinationRequests(int floor) {
        return this.totalDestinationRequests[floor];
    }

    public int getArrivedPassengers(int floor) {
        return this.arrivedPassengers[floor];
    }

    public int getPassengerRequests(int floor) {
        return this.passengerRequests[floor];
    }

    public void setTotalDestinationRequests(int floor, int value) {
        this.totalDestinationRequests[floor] += value;
    }

    public void setArrivedPassengers(int floor, int value) {
        this.arrivedPassengers[floor] += value;
    }

    public void setPassengerRequests(int floor, int value) {
        this.passengerRequests[floor] += value;
    }
}
