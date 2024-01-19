package rpg.potato.app.events;

import org.springframework.stereotype.Component;

import static rpg.potato.app.Attribute.*;

@Component
public class DoorEventFactory {

    public Event generateDoorEvent(int diceRoll) {
        Event doorEvent = new Event();
        switch (diceRoll) {
            case 1 -> {
                doorEvent.setMessage("A distant cousin. They are after you potatoes. They may snitch on you.");
                doorEvent.setModifier(ORCS, 1);
            }
            case 2 -> {
                doorEvent.setMessage("A dwarven stranger. You refuse them entry. Ghastly creatures.");
                doorEvent.setModifier(DESTINY, 1);
            }
            case 3 -> {
                doorEvent.setMessage("A wizard strolls by. You pointedly draw the curtains.");
                doorEvent.setModifier(ORCS, 1);
                doorEvent.setModifier(DESTINY, 1);
            }
            case 4 -> {
                doorEvent.setMessage("There are rumours of war in the reaches. You eat some potatoes.");
                doorEvent.setModifier(POTATOES, -1);
                doorEvent.setModifier(ORCS, 2);
            }
            case 5 -> {
                doorEvent.setMessage("It's an elf. They are not serious people.");
                doorEvent.setModifier(DESTINY, 1);
            }
            case 6 -> {
                doorEvent.setMessage("It's a sack of potatoes from a generous neighbour. You really must remember " +
                        "to pay them a visit one of these years.");
                doorEvent.setModifier(POTATOES, 2);
            }
        }
        return doorEvent;
    }
}
