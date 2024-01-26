package rpg.potato.models;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Dice {
    private static final Random rnd = new Random();

    public int roll() {
        return rnd.nextInt(6) + 1;
    }
}
