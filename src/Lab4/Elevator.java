package Lab4;

import java.util.ArrayList;
import java.util.ArrayDeque;

public class Elevator implements Runnable {
    int elevatorID;
    int currentFloor;
    int numPassengers;
    int totalLoadedPassengers;
    int totalUnloadedPassengers;
    ArrayDeque<ElevatorEvent> moveQueue;
    int[] passengerDestinations;
    BuildingManager manager;

    public Elevator(int elevatorID, BuildingManager manager) {
        this.elevatorID = elevatorID;
        this.numPassengers = 0;
        this.totalLoadedPassengers = 0;
        this.totalUnloadedPassengers = 0;
        this.moveQueue = new ArrayDeque<ElevatorEvent>();
        int[] passengerDestinations = new int[5];
        this.manager = manager;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (moveQueue.peek() != null) {

            }
        }
    }

    public void queueMove(ElevatorEvent move) {
        this.moveQueue.add(move);
    }
}
