package Lab4;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class ElevatorSimulation {
    int simulationLength;
    int simulatedSecondsRate;
    HashMap<Integer, ArrayList<PassengerArrival>> passengerArrivals;
    Elevator[] elevators;
    BuildingManager buildingManager;
    SimClock clock;

    public ElevatorSimulation() {
        this.passengerArrivals = new HashMap<Integer, ArrayList<PassengerArrival>>();
        this.buildingManager = new BuildingManager();
        this.clock = new SimClock();

        this.elevators = new Elevator[5];
        for (int i = 0; i < this.elevators.length; i++ )
            this.elevators[i] = new Elevator(i, this.buildingManager);
    };

    public void start() {
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
            threads[i].start();
        }

        while (this.clock.getTime() <=  this.simulationLength) {
            this.clock.tick();
        }

    }

    public void printBuildingState() {

    }

    public void readConfig(String fileName) {
        //Config file will always be 7 lines long
        String configArray[] = new String[7];

        try {
            String line;
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
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            return;
        }

        this.simulationLength = Integer.parseInt(configArray[0]);
        this.simulatedSecondsRate = Integer.parseInt(configArray[1]);

        for (int i = 2, floor = 0; i < 7; i++, floor++) {
            ArrayList<PassengerArrival> passengerArrivals = new ArrayList<PassengerArrival>();
            // If ";" is in the line then there are multiple passenger arrival rates for that floor
            if (configArray[i].contains(";")) {
                String[] arrivalRates = configArray[1].split(";");
                for (int k = 0; k < arrivalRates.length; k++) {
                    String[] arrivalRate = arrivalRates[k].split(" ");
                    passengerArrivals.add(new PassengerArrival(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2])));
                }
            }
            else {
                String[] arrivalRate = configArray[i].split(" ");
                passengerArrivals.add(new PassengerArrival(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2])));
            }
            this.passengerArrivals.put(floor, passengerArrivals);
        }

        return;
    }
}
