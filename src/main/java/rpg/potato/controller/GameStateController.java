package rpg.potato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpg.potato.factories.EndEventFactory;
import rpg.potato.services.GameStateModelAssembler;
import rpg.potato.models.Dice;
import rpg.potato.services.GameHandler;
import rpg.potato.models.Event;
import rpg.potato.factories.EventFactoryFassade;
import rpg.potato.exceptions.EventNotFoundException;
import rpg.potato.models.GameStateEntity;
import rpg.potato.repositories.GameStateRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
@RestController
@RequestMapping("/game")
public class GameStateController {
    private final GameHandler gameHandler;
    @Autowired
    private final EventFactoryFassade eventFactory;
    private final GameStateRepository repository;
    private final GameStateModelAssembler assembler;
    @Autowired
    private final Dice dice;

    public GameStateController(GameStateRepository repository, GameStateModelAssembler assembler) {
        this.gameHandler = new GameHandler();
        this.dice = new Dice();
        this.eventFactory = new EventFactoryFassade();
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/events")
    public CollectionModel<EntityModel<GameStateEntity>> all() {

        List<EntityModel<GameStateEntity>> events = repository.findAll().stream() //
                .map(assembler::toModel) //
                .toList();

        return CollectionModel.of(events, linkTo(methodOn(GameStateController.class).all()).withSelfRel());
    }

    @GetMapping("{id}")
    public EntityModel<GameStateEntity> one(@PathVariable Long id) {

        GameStateEntity game = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return assembler.toModel(game);
    }

    @PostMapping("/apply")
    public ResponseEntity<EntityModel<GameStateEntity>> applyEvent() {

        int firstDiceRoll = dice.roll();
        Event nextEvent = eventFactory.generateEvent(firstDiceRoll);

        GameStateEntity entity = gameHandler.applyEvent(nextEvent);

        EntityModel<GameStateEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/removeOrc")
    public ResponseEntity<EntityModel<GameStateEntity>> removeOrc() {

        GameStateEntity entity = gameHandler.applyScaling();

        EntityModel<GameStateEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

    @PutMapping("/newGame")
    public ResponseEntity<EntityModel<GameStateEntity>> newGame() {
        GameStateEntity entity = gameHandler.startNewGame();

        EntityModel<GameStateEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

}