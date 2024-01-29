package rpg.potato.models;

public class Score {
    private int value;
    public Score() {
        this.value = 0;
    }

    public boolean isFinished() {
        return value >= 10;
    }

    public void reset() {
        this.value = 0;
    }

    public void consume(int modifier) {
        this.value += modifier;
    }

}
