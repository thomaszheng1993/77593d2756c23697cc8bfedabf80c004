package Lab4;

/**
 * Created by shwil on 11/15/2016.
 */
public class BuildingManager {
    public BuildingFloor[] floors;

    public BuildingManager() {
        floors = new BuildingFloor[5];
        for (int i = 0; i < 5; i++) {
            floors[i] = new BuildingFloor();
        }
    }
}
