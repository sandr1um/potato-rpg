package rpg.potato.app.events;
public abstract class DiceEvent extends Event {
    public abstract Event generateEvent(int diceResult);
}
