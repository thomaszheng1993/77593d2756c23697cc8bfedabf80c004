package Lab4;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class ElevatorSimulation {
    int simulationLength;
    int simulatedSecondsRate;
    HashMap<Integer, ArrayList<ArrivalRate>> arrivalRates;

    public ElevatorSimulation() {
        this.arrivalRates = new HashMap<Integer, ArrayList<ArrivalRate>>();
    };

    public void start() {
        if (arrivalRates.isEmpty()) {
            return; //throw an exception here
        }



    }

    public void printBuildingState() {

    }

    public void readConfig() {
        //Config file will always be 7 lines long
        String configArray[] = new String[7];

        try {
            String line;
            Scanner inFile = new Scanner(new File("file.txt"));

            int i = 0;
            while (inFile.hasNextLine()) {
                configArray[i] = inFile.nextLine();
                i++;
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        this.simulationLength = Integer.parseInt(configArray[0]);
        this.simulatedSecondsRate = Integer.parseInt(configArray[1]);

        for (int i = 2, floor = 0; i < 7; i++, floor++) {
            ArrayList<ArrivalRate> passengerArrivals = new ArrayList<ArrivalRate>();
            // If ";" is in the line then there are multiple passenger arrival rates for that floor
            if (configArray[i].contains(";")) {
                String[] arrivalRates = configArray[1].split(";");
                for (int k = 0; k < arrivalRates.length; k++) {
                    String[] arrivalRate = arrivalRates[k].split(" ");
                    passengerArrivals.add(new ArrivalRate(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2])));
                }
            }
            else {
                String[] arrivalRate = configArray[i].split(" ");
                passengerArrivals.add(new ArrivalRate(Integer.parseInt(arrivalRate[0]), Integer.parseInt(arrivalRate[1]), Integer.parseInt(arrivalRate[2])));
            }
            this.arrivalRates.put(floor, passengerArrivals);
        }

        return;
    }
}
