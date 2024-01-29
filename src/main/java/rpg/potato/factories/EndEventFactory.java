package rpg.potato.factories;

import rpg.potato.enums.Attribute;
import rpg.potato.models.Event;

public class EndEventFactory {
    public Event generateEndEvent(Attribute endAttribute) {
        Event endEvent = new Event();

        switch (endAttribute) {
            case DESTINY -> {
                endEvent.setMessage("An interfering bard or wizard turns up at your doorstep with a quest, " +
                        "and you are whisked away against your will on an adventure.");
            }
            case POTATOES -> {
                endEvent.setMessage("You have enough potatoes that you can go underground and not return to the " +
                        "surface until the danger is past. You nestle down into your burrow and enjoy your well earned rest");
            }
            case ORCS -> {
                endEvent.setMessage("Orcs have finally find your potato farm. Alas, orcs are not so interested " +
                        "in potatoes as they are in eating you, and you end up in a cock pot");
            }
        }

        return endEvent;
    }
}
