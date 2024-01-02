package rpg.potato.app.events;

import rpg.potato.app.Attribute;

public class DoorEvent extends Event {

    public DoorEvent(int diceRoll) {
        switch (diceRoll) {
            case 1 -> {
                super.message = "A distant cousin. They are after you potatoes. They may snitch on you.";
                super.result.setAttribute(Attribute.ORCS, 1);
            }
            case 2 -> {
                super.message = "A dwarven stranger. You refuse them entry. Ghastly creatures.";
                super.result.setAttribute(Attribute.DESTINY, 1);
            }
            case 3 -> {
                super.message = "A wizard strolls by. You pointedly draw the curtains.";
                super.result.setAttribute(Attribute.ORCS, 1);
                super.result.setAttribute(Attribute.DESTINY, 1);
            }
            case 4 -> {
                super.message = "There are rumours of war in the reaches. You eat some potatoes.";
                super.result.setAttribute(Attribute.POTATOES, -1);
                super.result.setAttribute(Attribute.ORCS, 2);
            }
            case 5 -> {
                super.message = "It's an elf. They are not serious people.";
                super.result.setAttribute(Attribute.DESTINY, 1);
            }
            case 6 -> {
                super.message = "It's a sack of potatoes from a generous neighbour. You really must remember " +
                        "to pay them a visit one of these years.";
                super.result.setAttribute(Attribute.POTATOES, 2);
            }
        }
    }
    @Override
    public Event generateEvent(int diceRoll) {
        return new DoorEvent(diceRoll);
    }
}
