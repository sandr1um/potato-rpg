package rpg.potato.app.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpg.potato.app.Dice;

@Component
public class EventFactoryFassade {

    @Autowired
    private Dice dice;
    @Autowired
    private GardenEventFactory gardenEventFactory;
    @Autowired
    private DoorEventFactory doorEventFactory;
    @Autowired
    private DarkEventFactory darkEventFactory;

    public Event generateEvent(int firstDiceRoll) {
        int secondDiceRoll = dice.roll();
        return switch (firstDiceRoll) {
            case 1, 2 -> gardenEventFactory.generateGardenEvent(secondDiceRoll);
            case 3, 4 -> doorEventFactory.generateDoorEvent(secondDiceRoll);
            case 5, 6 -> darkEventFactory.generateDarkEvent();
            default -> new Event(); //Unreachable statement
        };
    }

}
