package rpg.potato.app.events;

import lombok.AllArgsConstructor;
import rpg.potato.app.Dice;

@AllArgsConstructor
public class EventFactory {
    Dice dice;

    public Event generateEvent() {
        int firstDiceRoll = dice.roll();
        return switch (firstDiceRoll) {
            case 1, 2 -> new GardenEventFactory().generateGardenEvent(dice.roll());
            case 3, 4 -> new DoorEventFactory().generateDoorEvent(dice.roll());
            case 5, 6 -> new DarkEvent();
            default -> new Event("", null);
        };
    }


}
