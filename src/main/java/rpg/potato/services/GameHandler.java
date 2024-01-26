package rpg.potato.services;

import rpg.potato.enums.Attribute;
import rpg.potato.models.Event;
import rpg.potato.models.GameStateEntity;

import java.util.EnumMap;

import static rpg.potato.enums.Attribute.*;


public class GameHandler {
    private final EnumMap<Attribute, Integer> gameStateScores;

    public GameHandler() {
        this.gameStateScores = new EnumMap<>(Attribute.class);
        this.gameStateScores.put(DESTINY, 0);
        this.gameStateScores.put(POTATOES, 0);
        this.gameStateScores.put(ORCS, 0);
        this.gameStateScores.put(SCALING, 1);
    }

    public int getScore(Attribute attribute) {
        return gameStateScores.get(attribute);
    }

    public GameStateEntity startNewGame() {
        gameStateScores.replaceAll((a, v) -> 0);
        gameStateScores.put(SCALING, 1);

        GameStateEntity gameState = new GameStateEntity();
        setGameStateValues(gameState, "New Game!");

        return gameState;
    }

    public GameStateEntity applyEvent(Event event) {
        GameStateEntity gameState = new GameStateEntity();
        
        if (!isFinished()) {
            event.getModifiers().forEach(this::changeScore);
        }
        
        setGameStateValues(gameState, event.getMessage());
        
        return gameState;
    }

    public GameStateEntity applyScaling() {
        GameStateEntity gameState = new GameStateEntity();
        if (getScore(ORCS) == 0) {
            setGameStateValues(gameState, "There are no orcs");
        } else if (getScore(POTATOES) >= getScore(SCALING)) {
            changeScore(ORCS, -1);
            changeScore(POTATOES, -1 * getScaling());
            setGameStateValues(gameState, "Removed one orc at the expense of " +
                    getScaling() +
                    (getScaling() == 1 ? " potato" : " potatoes"));

        } else {
            setGameStateValues(gameState, "Not enough potatoes");
        }

        return gameState;
    }

    private void setGameStateValues(GameStateEntity gameState, String message) {
        gameState.setDestiny(getScore(DESTINY));
        gameState.setPotatoes(getScore(POTATOES));
        gameState.setOrcs(getScore(ORCS));
        gameState.setScaling(getScore(SCALING));
        gameState.setMessage(message);
    }

    public boolean isFinished() {
        return gameStateScores.keySet()
                .stream()
                .anyMatch(item -> gameStateScores.get(item) == 10 && !item.equals(Attribute.SCALING));
    }

    private void changeScore(Attribute attribute, int change) {
        int changes = gameStateScores.get(attribute) + change;
        if (changes >= 0) {
            gameStateScores.put(attribute, changes);
        }
    }

    public String generateFinalMessage() {
        Attribute endAttribute = gameStateScores
                .keySet()
                .stream()
                .filter(item -> gameStateScores.get(item) == 10 && !item.equals(Attribute.SCALING))
                .findAny()
                .orElseThrow();

        return switch (endAttribute) {
            case DESTINY -> "An interfering bard or wizard turns up at your doorstep with a quest," +
                    " and you are whisked away against your will on an adventure.";
            case POTATOES -> "You have enough potatoes that you can go underground and not return to the surface" +
                    " until the danger is past. You nestle down into your burrow and enjoy your well earned rest";
            case ORCS ->
                    "Orcs have finally find your potato farm. Alas, orcs are not so interested in potatoes as they" +
                            " are in eating you, and you end up in a cock pot";
            case SCALING -> "";
        };
    }

    public int getScaling() {
        return gameStateScores.get(Attribute.SCALING);
    }
}