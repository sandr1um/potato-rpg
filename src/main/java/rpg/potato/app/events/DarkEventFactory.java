package rpg.potato.app.events;

import org.springframework.stereotype.Component;

import static rpg.potato.app.Attribute.SCALING;

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
