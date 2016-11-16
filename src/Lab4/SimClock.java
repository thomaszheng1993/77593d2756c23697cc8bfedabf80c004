package Lab4;

public class SimClock {
    private int simulatedTime;

    public SimClock() {
        this.simulatedTime = 0;
    }
    public void tick() {
        this.simulatedTime += 1;
    }

    public int getTime() {
        return this.simulatedTime;
    }

}
