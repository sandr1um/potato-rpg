package rpg.potato.app;

import java.util.Random;

public class Dice {
    private static final Random rnd = new Random();

    public int roll() {
        return rnd.nextInt(6) + 1;
    }
}
