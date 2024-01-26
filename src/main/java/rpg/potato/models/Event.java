package rpg.potato.models;

import lombok.Getter;
import lombok.Setter;
import rpg.potato.enums.Attribute;

import java.util.EnumMap;

@Getter
@Setter
public class Event {
    private String message;
    private EnumMap<Attribute, Integer> modifiers;

    public Event() {
        this.message = null;
        this.modifiers = new EnumMap<>(Attribute.class);
    }

    public void setModifier(Attribute attribute, int value) {
        modifiers.put(attribute, value);
    }
}
