package Lab4;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorSimulation {
    int simulationLength;
    int simulatedSecondsRate;
    HashMap<Integer, Queue<PassengerArrival>> passengerArrivals;
    Elevator[] elevators;
    BuildingManager buildingManager;
    SimClock clock;

    public ElevatorSimulation() {
        this.passengerArrivals = new HashMap<Integer, Queue<PassengerArrival>>();
        this.buildingManager = new BuildingManager();
        this.clock = new SimClock();

        this.elevators = new Elevator[5];
        for (int i = 0; i < this.elevators.length; i++ )
            this.elevators[i] = new Elevator(i, this.buildingManager);
    };

    public void start() {
        //this happens if start() is called before readConfig()
        if (passengerArrivals.isEmpty()) {
            return; //throw an exception here
        }

        Elevator[] elevators = new Elevator[5];
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            elevators[i] = new Elevator(i, this.buildingManager);
        }
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(elevators[i]);
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }

        while (this.clock.getTime() <= this.simulationLength) {
            for (HashMap.Entry<Integer, Queue<PassengerArrival>>  entry : passengerArrivals.entrySet()) {
                int floor = entry.getKey();
                Queue<PassengerArrival> arrivals = entry.getValue();

                for (PassengerArrival arrival: arrivals) {
                    if (arrival.expectedTimeOfArrival == this.clock.getTime()){
                        passengerArrivals.remove(arrival);
                        System.out.format("| TIME %4d | ELEVATOR #N/A | %d PASSENGER IS REQUESTING FROM FLOOR #%d TO FLOOR #%d\n\n", this.clock.getTime(), arrival.numPassengers, floor, arrival.destinationFloor);
                        arrivals.add(new PassengerArrival(arrival.numPassengers, arrival.destinationFloor, arrival.timePeriod, this.clock.getTime() + arrival.timePeriod));
                    }
                    buildingManager.setPassengerRequests(floor, arrival.destinationFloor, arrival.numPassengers);
                    //elevators[floor].queueMove(new ElevatorEvent(arrival.destinationFloor, arrival.expectedTimeOfArrival));
                }
            }
            this.clock.tick();
        }

        //interrupts all the elevator threads
        for (int i = 0; i < 5; i++) {
            threads[i].interrupt();
        }
        return;

    }

    public void printBuildingState() {

    }

    public void readConfig(String fileName) {
        //Config file will always be 7 lines long
        String configArray[] = new String[7];

        try {
            Scanner inFile = new Scanner(new File(fileName));

            int i = 0;
            while (inFile.hasNextLine()) {
                configArray[i] = inFile.nextLine();
                i++;
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
            return;
        }

        this.simulationLength = Integer.parseInt(configArray[0]);
        this.simulatedSecondsRate = Integer.parseInt(configArray[1]);

        for (int i = 2, floor = 0; i < 7; i++, floor++) {
            Queue<PassengerArrival> passengerArrivals = new ConcurrentLinkedQueue<PassengerArrival>();
            // If ";" is in the line then there are multiple passenger arrival rates for that floor
            if (configArray[i].contains(";")) {
                String[] arrivalRates = configArray[i].split(";");
                for (int k = 0; k < arrivalRates.length; k++) {
                    String[] arrivalRate = arrivalRates[k].split(" ");
                    passengerArrivals.add(new PassengerArrival(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2]), Integer.parseInt(arrivalRate[2])));
                }
            }
            else {
                String[] arrivalRate = configArray[i].split(" ");
                passengerArrivals.add(new PassengerArrival(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2]), Integer.parseInt(arrivalRate[2])));
            }
            this.passengerArrivals.put(floor, passengerArrivals);
        }

        return;
    }
}
