package Lab4;

import java.util.ArrayList;

public class Elevator {
    int elevatorID;
    int currentFloor;
    int numPassengers;
    int totalLoadedPassengers;
    int totalUnloadedPassengers;
    ArrayList<ElevatorEvent> moveQueue;
    int[] passengerDestinations;
    BuildingManager manager;

    public Elevator(int elevatorID, BuildingManager manager) {
        this.elevatorID = elevatorID;
        this.numPassengers = 0;
        this.totalLoadedPassengers = 0;
        this.totalUnloadedPassengers = 0;
        int[] passengerDestinations = new int[5];
        this.manager = manager;
    }

    public void run() {
        while (!moveQueue.isEmpty()) {

        }
    }
}
