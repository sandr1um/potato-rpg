package rpg.potato.app.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import rpg.potato.app.Attribute;

import java.util.EnumMap;


@AllArgsConstructor
@Getter
public class Event {
    protected String message;
    protected EnumMap<Attribute, Integer> modifiers;
}
