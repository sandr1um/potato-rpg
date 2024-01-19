package rpg.potato.app.events;

import org.springframework.stereotype.Component;

import static rpg.potato.app.Attribute.*;

@Component
public class GardenEventFactory {
    public Event generateGardenEvent(int diceRoll) {
        Event gardenEvent = new Event();
        switch (diceRoll) {
            case 1 -> {
                gardenEvent.setMessage("You happily root about all day in your garden");
                gardenEvent.setModifier(POTATOES, 1);
            }
            case 2 -> {
                gardenEvent.setMessage("You narrowly avoid a visitor by hiding in a potato sack.");
                gardenEvent.setModifier(DESTINY, 1);
                gardenEvent.setModifier(POTATOES, 1);
            }
            case 3 -> {
                gardenEvent.setMessage("A hooded stranger lingers outside your farm");
                gardenEvent.setModifier(DESTINY, 1);
                gardenEvent.setModifier(ORCS, 1);
            }
            case 4 -> {
                gardenEvent.setMessage("Your field is ravaged in the night by unseen enemies.");
                gardenEvent.setModifier(ORCS, 1);
                gardenEvent.setModifier(POTATOES, -1);
            }
            case 5 -> {
                gardenEvent.setMessage("You trade potatoes for other delicious foodstuffs");
                gardenEvent.setModifier(POTATOES, -1);
            }
            case 6 -> {
                gardenEvent.setMessage("You burrow into a bumper crop of potatoes. Do you cry with joy? Possibly.");
                gardenEvent.setModifier(POTATOES, 2);
            }
        }
        return gardenEvent;
    }

}
