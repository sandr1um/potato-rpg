package rpg.potato.app.events;

import rpg.potato.app.Attribute;

import java.util.EnumMap;

import static rpg.potato.app.Attribute.*;
import static rpg.potato.app.Attribute.POTATOES;

public class DoorEventFactory {

    public Event generateDoorEvent(int diceRoll) {
        String message = "";
        EnumMap<Attribute, Integer> modifiers = new EnumMap<>(Attribute.class);
        switch (diceRoll) {
            case 1 -> {
                message = "A distant cousin. They are after you potatoes. They may snitch on you.";
                modifiers.put(ORCS, 1);
            }
            case 2 -> {
                message = "A dwarven stranger. You refuse them entry. Ghastly creatures.";
                modifiers.put(DESTINY, 1);
            }
            case 3 -> {
                message = "A wizard strolls by. You pointedly draw the curtains.";
                modifiers.put(ORCS, 1);
                modifiers.put(DESTINY, 1);
            }
            case 4 -> {
                message = "There are rumours of war in the reaches. You eat some potatoes.";
                modifiers.put(POTATOES, -1);
                modifiers.put(ORCS, 2);
            }
            case 5 -> {
                message = "It's an elf. They are not serious people.";
                modifiers.put(DESTINY, 1);
            }
            case 6 -> {
                message = "It's a sack of potatoes from a generous neighbour. You really must remember " +
                        "to pay them a visit one of these years.";
                modifiers.put(POTATOES, 2);
            }
        }
        return new Event(message, modifiers);
    }
}
