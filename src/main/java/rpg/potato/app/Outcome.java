package rpg.potato.app;

import java.util.HashMap;
import java.util.Map;

public class Outcome {
    private final Map<Attribute, Integer> attributesToChange;

    public Outcome() {
        this.attributesToChange = new HashMap<>();
        this.attributesToChange.put(Attribute.DESTINY, 0);
        this.attributesToChange.put(Attribute.POTATOES, 0);
        this.attributesToChange.put(Attribute.ORCS, 0);
        this.attributesToChange.put(Attribute.SCALING, 0);
    }

    public void setAttribute(Attribute attribute, int value) {
        this.attributesToChange.put(attribute, value);
    }

    public int getChange(Attribute attribute) {
        return attributesToChange.get(attribute);
    }
}
