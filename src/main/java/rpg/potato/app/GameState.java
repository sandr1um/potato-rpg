package rpg.potato.app;

import rpg.potato.app.Attribute;
import rpg.potato.app.events.Event;
import rpg.potato.app.events.HurlingEvent;
import rpg.potato.event.EventEntity;

import java.util.HashMap;
import java.util.Map;

import static rpg.potato.app.Attribute.*;


public class GameState {
    private final Map<Attribute, Integer> scores;
    public GameState() {
        this.scores = new HashMap<>();
        this.scores.put(DESTINY, 0);
        this.scores.put(POTATOES, 0);
        this.scores.put(ORCS, 0);
        this.scores.put(SCALING, 1);
    }
    public int getScore(Attribute attribute) {
        return scores.get(attribute);
    }

    private void hurlingEventHandling(Event event) {
        if (getScaling() > getScore(POTATOES)) {
            event.setMessage("You have not enough potatoes.");
        } else if (getScore(ORCS) == 0) {
            event.setMessage("No Orcs to remove.");
        } else {
            scores.put(POTATOES, getScore(POTATOES) - getScaling());
            scores.put(ORCS, getScore(ORCS) - 1);
        }
    }

    public EventEntity startNewGame() {
        for (Attribute attribute : scores.keySet()) {
            scores.put(attribute, 0);
        }
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
            if (event instanceof HurlingEvent) {
                hurlingEventHandling(event);
            } else {
                scores.keySet().forEach(item -> changeScore(item, event.getResult().getChange(item)));
            }
        }
        return new EventEntity(
                getScore(DESTINY),
                getScore(POTATOES),
                getScore(ORCS),
                getScore(SCALING),
                event.getMessage());
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
                    " are in eating you, and you end up in a cockpot";
            case SCALING -> "";
        };
    }

    public int getScaling() {
        return scores.get(Attribute.SCALING);
    }
}