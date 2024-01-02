package rpg.potato.app.events;

import rpg.potato.app.Attribute;

import java.util.EnumMap;

import static rpg.potato.app.Attribute.SCALING;

public class DarkEvent extends Event {

    public DarkEvent() {
        super("", null);
        EnumMap<Attribute, Integer> modifiers = new EnumMap<>(Attribute.class);
        modifiers.put(SCALING, 1);
        super.message = "The world becomes a darker, more dangerous place. " +
                "From now on, removing ORC costs an additional POTATO.";
        super.modifiers = modifiers;
    }

}
