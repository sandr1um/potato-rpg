package rpg.potato.app;

import rpg.potato.app.events.Event;
import rpg.potato.event.EventEntity;

import java.util.EnumMap;

import static rpg.potato.app.Attribute.*;


public class GameHandler {
    private final EnumMap<Attribute, Integer> scores;
    public GameHandler() {
        this.scores = new EnumMap<>(Attribute.class);
        this.scores.put(DESTINY, 0);
        this.scores.put(POTATOES, 0);
        this.scores.put(ORCS, 0);
        this.scores.put(SCALING, 1);
    }
    public int getScore(Attribute attribute) {
        return scores.get(attribute);
    }

    public EventEntity startNewGame() {
        scores.replaceAll((a, v) -> 0);
        scores.put(SCALING, 1);

        return new EventEntity(
                getScore(DESTINY),
                getScore(POTATOES),
                getScore(ORCS),
                getScore(SCALING),
                "New Game!");
    }

    public EventEntity applyEvent(Event event) {
        if (!isFinished()) {
            event.getModifiers().forEach(this::changeScore);
        }
        return new EventEntity(
                getScore(DESTINY),
                getScore(POTATOES),
                getScore(ORCS),
                getScore(SCALING),
                event.getMessage());
    }

    public EventEntity applyScaling() {
        String message;
        if (getScore(ORCS) == 0) {
            message = "There are no orcs";
        } else if (getScore(POTATOES) >= getScore(SCALING)) {
            changeScore(ORCS, -1);
            changeScore(POTATOES, -1 * getScaling());
            message = "Removed one orc at the expense of " +
                    getScaling() +
                    (getScaling() == 1 ? " potato" : " potatoes");
        } else {
            message = "Not enough potatoes";
        }

        return new EventEntity(
                getScore(DESTINY),
                getScore(POTATOES),
                getScore(ORCS),
                getScore(SCALING),
                message
        );
    }

    public boolean isFinished() {
        return scores.keySet()
                .stream()
                .anyMatch(item -> scores.get(item) == 10 && !item.equals(Attribute.SCALING));
    }

    private void changeScore(Attribute attribute, int change) {
        int changes = scores.get(attribute) + change;
        if (changes >= 0) {
            scores.put(attribute, changes);
        }
    }

    public String generateFinalMessage() {
        Attribute endAttribute = scores
                .keySet()
                .stream()
                .filter(item -> scores.get(item) == 10 && !item.equals(Attribute.SCALING))
                .findAny()
                .get();

        return switch (endAttribute) {
            case DESTINY ->"An interfering bard or wizard turns up at your doorstep with a quest," +
                    " and you are whisked away against your will on an adventure.";
            case POTATOES -> "You have enough potatoes that you can go underground and not return to the surface" +
                    " until the danger is past. You nestle down into your burrow and enjoy your well earned rest";
            case ORCS -> "Orcs have finally find your potato farm. Alas, orcs are not so interested in potatoes as they" +
                    " are in eating you, and you end up in a cock pot";
            case SCALING -> "";
        };
    }

    public int getScaling() {
        return scores.get(Attribute.SCALING);
    }
}