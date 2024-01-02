package rpg.potato.app.events;

import static rpg.potato.app.Attribute.ORCS;
import static rpg.potato.app.Attribute.POTATOES;

public class HurlingEvent extends Event {

    public HurlingEvent(int scaling) {
        super.message = "1 Orc removed. " + scaling + " potatoes used.";
        super.result.setAttribute(ORCS, -1);
        super.result.setAttribute(POTATOES, -1 * scaling);
    }
    @Override
    public Event generateEvent(int scaling) {
        return new HurlingEvent(scaling);
    }
}