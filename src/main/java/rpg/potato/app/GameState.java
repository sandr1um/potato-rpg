package rpg.potato.app;

import java.util.EnumMap;
import java.util.Map;

import static rpg.potato.app.Attribute.*;

public class GameState {
    private final Map<Attribute, Integer> scores;

    public GameState() {
        this.scores = new EnumMap<>(Attribute.class);
        //Initial values for new game
        this.scores.put(DESTINY, 0);
        this.scores.put(POTATOES, 0);
        this.scores.put(ORCS, 0);
        this.scores.put(SCALING, 1);
    }
    public int getScore(Attribute attribute) {
        return scores.get(attribute);
    }


}
