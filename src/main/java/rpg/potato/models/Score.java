package rpg.potato.models;

import lombok.Getter;

@Getter
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
        this.value = Math.max(this.value, this.value + modifier);
        this.value = Math.min(10, this.value);
    }

}
