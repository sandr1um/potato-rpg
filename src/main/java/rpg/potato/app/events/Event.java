package rpg.potato.app.events;

import lombok.Getter;
import lombok.Setter;
import rpg.potato.app.Outcome;

@Getter
public class Event {
    @Setter
    protected String message = "";
    protected Outcome result = new Outcome();
}
