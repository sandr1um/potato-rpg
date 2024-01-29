package rpg.potato.services;

import lombok.Getter;
import rpg.potato.enums.Attribute;
import rpg.potato.factories.EndEventFactory;
import rpg.potato.models.DarkEvent;
import rpg.potato.models.Event;
import rpg.potato.models.GameStateEntity;
import rpg.potato.models.Score;

import java.util.EnumMap;

import static rpg.potato.enums.Attribute.*;


public class GameHandler {
    private final EnumMap<Attribute, Score> gameStateScores;
    @Getter
    private int scaling;
    @Getter
    private boolean isGameFinished;

    public GameHandler() {
        this.gameStateScores = new EnumMap<>(Attribute.class);
        for (Attribute attribute : Attribute.values()) {
            this.gameStateScores.put(attribute, new Score());
        }
        this.scaling = 1;
        this.isGameFinished = false;
    }

    public int getScore(Attribute attribute) {
        return gameStateScores.get(attribute).getValue();
    }

    public GameStateEntity startNewGame() {
        gameStateScores.forEach((a, v) -> v.reset());
        this.scaling = 1;
        this.isGameFinished = false;

        GameStateEntity gameState = new GameStateEntity();
        setGameStateValues(gameState, "New Game!");

        return gameState;
    }

    public GameStateEntity applyEvent(Event event) {
        GameStateEntity gameState = new GameStateEntity();

        if (isGameFinished) {
            Attribute endAttribute = gameStateScores
                    .keySet()
                    .stream()
                    .filter(attribute -> gameStateScores.get(attribute).isFinished())
                    .findFirst()
                    .get();

            Event endEvent = new EndEventFactory().generateEndEvent(endAttribute);
            setGameStateValues(gameState, endEvent.getMessage());
        } else {
            if (event instanceof DarkEvent) {
                this.scaling++;
                setGameStateValues(gameState, event.getMessage());
            } else {
                gameStateScores
                        .keySet()
                        .forEach(attribute -> gameStateScores.get(attribute).consume(event.getModifier(attribute)));
                setGameStateValues(gameState, event.getMessage());
            }
        }

        isGameFinished = gameStateScores.values().stream().anyMatch(Score::isFinished);

        return gameState;
    }

    public GameStateEntity applyScaling() {
        GameStateEntity gameState = new GameStateEntity();

        if (isGameFinished) {
            Attribute endAttribute = gameStateScores
                    .keySet()
                    .stream()
                    .filter(attribute -> gameStateScores.get(attribute).isFinished())
                    .findFirst()
                    .get();

            Event endEvent = new EndEventFactory().generateEndEvent(endAttribute);
            setGameStateValues(gameState, endEvent.getMessage());
        } else {
            if (getScore(ORCS) == 0) {
                setGameStateValues(gameState, "There are no orcs");
            } else if (getScore(POTATOES) >= scaling) {
                gameStateScores.get(ORCS).consume(-1);
                gameStateScores.get(POTATOES).consume(-1 * scaling);
                setGameStateValues(gameState, "Removed one orc at the expense of " +
                        getScaling() +
                        (getScaling() == 1 ? " potato" : " potatoes"));

            } else {
                setGameStateValues(gameState, "Not enough potatoes");
            }
        }

        return gameState;
    }

    private void setGameStateValues(GameStateEntity gameState, String message) {
        gameState.setDestiny(getScore(DESTINY));
        gameState.setPotatoes(getScore(POTATOES));
        gameState.setOrcs(getScore(ORCS));
        gameState.setScaling(scaling);
        gameState.setMessage(message);
    }
}