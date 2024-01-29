package rpg.potato.factories;

import org.springframework.stereotype.Component;
import rpg.potato.models.DarkEvent;
import rpg.potato.models.Event;

@Component
public class DarkEventFactory {
    public Event generateDarkEvent() {
        DarkEvent darkEvent = new DarkEvent();
        darkEvent.setScalingChange(1);
        darkEvent.setMessage("The world becomes a darker, more dangerous place. " +
                "From now on, removing ORC costs an additional POTATO.");
        return darkEvent;
    }
}
