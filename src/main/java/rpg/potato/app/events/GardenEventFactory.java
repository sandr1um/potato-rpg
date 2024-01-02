package rpg.potato.app.events;

import rpg.potato.app.Attribute;

import java.util.EnumMap;

import static rpg.potato.app.Attribute.*;

public class GardenEventFactory {
    public Event generateGardenEvent(int diceRoll) {
        String message = "";
        EnumMap<Attribute, Integer> modifiers = new EnumMap<>(Attribute.class);
        switch (diceRoll) {
            case 1 -> {
                message = "You happily root about all day in your garden";
                modifiers.put(POTATOES, 1);
            }
            case 2 -> {
                message = "You narrowly avoid a visitor by hiding in a potato sack.";
                modifiers.put(DESTINY, 1);
                modifiers.put(POTATOES, 1);
            }
            case 3 -> {
                message = "A hooded stranger lingers outside your farm";
                modifiers.put(DESTINY, 1);
                modifiers.put(ORCS, 1);
            }
            case 4 -> {
                message = "Your field is ravaged in the night by unseen enemies.";
                modifiers.put(ORCS, 1);
                modifiers.put(POTATOES, -1);
            }
            case 5 -> {
                message = "You trade potatoes for other delicious foodstuffs";
                modifiers.put(POTATOES, -1);
            }
            case 6 -> {
                message = "You burrow into a bumper crop of potatoes. Do you cry with joy? Possibly.";
                modifiers.put(POTATOES, 2);
            }
        }
        return new Event(message, modifiers);
    }

}
