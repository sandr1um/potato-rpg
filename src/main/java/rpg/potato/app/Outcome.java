package rpg.potato.app;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static rpg.potato.app.Attribute.*;

public class Outcome {
    private final EnumMap<Attribute, Integer> attributesToChange;

    public Outcome() {
        this.attributesToChange = new EnumMap<>(Attribute.class);
        this.attributesToChange.put(DESTINY, 0);
        this.attributesToChange.put(POTATOES, 0);
        this.attributesToChange.put(ORCS, 0);
        this.attributesToChange.put(SCALING, 0);
    }

    public void setAttribute(Attribute attribute, int value) {
        this.attributesToChange.put(attribute, value);
    }

    public int getChange(Attribute attribute) {
        return attributesToChange.get(attribute);
    }
}
