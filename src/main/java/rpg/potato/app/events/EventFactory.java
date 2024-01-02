package rpg.potato.app.events;

import lombok.AllArgsConstructor;
import rpg.potato.app.Dice;

@AllArgsConstructor
public class EventFactory {
    Dice dice;
    public Event generateEvent(int firstDiceRoll) {
        int secondDiceRoll = dice.roll();
        return switch (firstDiceRoll) {
            case 1, 2 -> new GardenEventFactory().generateGardenEvent(secondDiceRoll);
            case 3, 4 -> new DoorEventFactory().generateDoorEvent(secondDiceRoll);
            case 5, 6 -> new DarkEvent();
            default -> new Event("", null); //Not reachable
        };
    }


}
