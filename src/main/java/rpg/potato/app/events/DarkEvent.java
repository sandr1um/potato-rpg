package rpg.potato.app.events;

import static rpg.potato.app.Attribute.SCALING;

public class DarkEvent extends NoDiceEvent {

    public DarkEvent() {
        super.result.setAttribute(SCALING, 1);
        super.message = "The world becomes a darker, more dangerous place. " +
                "From now on, removing ORC costs an additional POTATO.";
    }

    @Override
    public Event generateEvent() {
        return new DarkEvent();
    }
}
