package rpg.potato.app.events;

import rpg.potato.app.Attribute;

public class GardenEvent extends Event {
    public GardenEvent(int diceRoll) {
        switch (diceRoll) {
            case 1 -> {
                super.message = "You happily root about all day in your garden";
                super.result.setAttribute(Attribute.POTATOES, 1);
            }
            case 2 -> {
                super.message = "You narrowly avoid a visitor by hiding in a potato sack.";
                super.result.setAttribute(Attribute.DESTINY, 1);
                super.result.setAttribute(Attribute.POTATOES, 1);
            }
            case 3 -> {
                super.message = "A hooded stranger lingers outside your farm";
                super.result.setAttribute(Attribute.DESTINY, 1);
                super.result.setAttribute(Attribute.ORCS, 1);
            }
            case 4 -> {
                super.message = "Your field is ravaged in the night by unseen enemies.";
                super.result.setAttribute(Attribute.ORCS, 1);
                super.result.setAttribute(Attribute.POTATOES, -1);
            }
            case 5 -> {
                super.message = "You trade potatoes for other delicious foodstuffs";
                super.result.setAttribute(Attribute.POTATOES, -1);
            }
            case 6 -> {
                super.message = "You burrow into a bumper crop of potatoes. Do you cry with joy? Possibly.";
                super.result.setAttribute(Attribute.POTATOES, 2);
            }
        }
    }

    @Override
    public Event generateEvent(int diceRoll) {
        return new GardenEvent(diceRoll);
    }
}
