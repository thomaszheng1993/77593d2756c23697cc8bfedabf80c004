package Lab4;

public class Lab4 {
    public static void main(String[] args) {
        ElevatorSimulation simulation = new ElevatorSimulation();
        simulation.readConfig("E:\\Windows 10\\Documents\\GitHub\\45JLab4\\src\\Lab4\\ElevatorConfig.txt");
        simulation.start();
    }
}
