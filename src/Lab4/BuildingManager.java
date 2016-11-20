package Lab4;

public class BuildingManager {
    public BuildingFloor[] floors;

    public BuildingManager() {
        floors = new BuildingFloor[5];
        for (int i = 0; i < 5; i++) {
            floors[i] = new BuildingFloor();
        }
    }

}
