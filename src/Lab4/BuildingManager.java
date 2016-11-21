package Lab4;

public class BuildingManager {
    public BuildingFloor[] floors;

    public BuildingManager() {
        floors = new BuildingFloor[5];
        for (int i = 0; i < 5; i++) {
            floors[i] = new BuildingFloor();
        }
    }

    public int getTotalDestinationRequests(int floor, int dest) {
        return this.floors[floor].getTotalDestinationRequests(dest);
    }

    public int getArrivedPassengers(int floor, int dest) {

        return this.floors[floor].getArrivedPassengers(dest);
    }

    public int getPassengerRequests(int floor, int dest) {
        return this.floors[floor].getPassengerRequests(dest);
    }

    public void setTotalDestinationRequests(int floor, int dest, int value) {

        this.floors[floor].setTotalDestinationRequests(dest, value);
    }

    public void setArrivedPassengers(int floor, int dest, int value) {

        this.floors[floor].setArrivedPassengers(dest, value);
    }

    public void setPassengerRequests(int floor, int dest, int value) {
        this.floors[floor].setPassengerRequests(dest, value);
    }

}
