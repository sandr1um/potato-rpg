package rpg.potato.factories;

import org.springframework.stereotype.Component;
import rpg.potato.models.Event;

import static rpg.potato.enums.Attribute.SCALING;

@Component
public class DarkEventFactory {
    public Event generateDarkEvent() {
        Event darkEvent = new Event();
        darkEvent.setModifier(SCALING, 1);
        darkEvent.setMessage("The world becomes a darker, more dangerous place. " +
                "From now on, removing ORC costs an additional POTATO.");
        return darkEvent;
    }
}
