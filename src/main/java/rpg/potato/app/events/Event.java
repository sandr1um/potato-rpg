package rpg.potato.app.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpg.potato.app.Attribute;

import java.util.EnumMap;

@NoArgsConstructor
@Getter
@Setter
public class Event {
    private String message;
    private EnumMap<Attribute, Integer> modifiers;
    public void setModifier(Attribute attribute, int value) {
        modifiers.put(attribute, value);
    }
}
