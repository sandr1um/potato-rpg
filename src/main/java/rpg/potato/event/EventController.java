package rpg.potato.event;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpg.potato.app.Dice;
import rpg.potato.app.GameHandler;
import rpg.potato.app.events.Event;
import rpg.potato.app.events.EventFactory;
import rpg.potato.exceptions.EventNotFoundException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
@RestController
@RequestMapping("/game")
public class EventController {
    private final GameHandler gameHandler;
    private final EventFactory eventFactory;
    private final EventRepository repository;
    private final EventModelAssembler assembler;
    private final Dice dice;

    public EventController(EventRepository repository, EventModelAssembler assembler) {
        this.gameHandler = new GameHandler();
        this.eventFactory = new EventFactory(new Dice());
        this.repository = repository;
        this.assembler = assembler;
        this.dice = new Dice();
    }

    @GetMapping("/events")
    CollectionModel<EntityModel<EventEntity>> all() {

        List<EntityModel<EventEntity>> events = repository.findAll().stream() //
                .map(assembler::toModel) //
                .toList();

        return CollectionModel.of(events, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @GetMapping("{id}")
    EntityModel<EventEntity> one(@PathVariable Long id) {

        EventEntity game = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        return assembler.toModel(game);
    }

    @PostMapping("/apply")
    public ResponseEntity<EntityModel<EventEntity>> applyEvent() {

        int firstDiceRoll = dice.roll();
        Event nextEvent = eventFactory.generateEvent(firstDiceRoll);

        EventEntity entity = gameHandler.applyEvent(nextEvent);

        if (gameHandler.isFinished()) {
            String finalMessage = gameHandler.generateFinalMessage();
            entity.setMessage(finalMessage);
        }

        EntityModel<EventEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping("/removeOrc")
    public ResponseEntity<EntityModel<EventEntity>> removeOrc() {

        EventEntity entity = gameHandler.applyScaling();

        if (gameHandler.isFinished()) {
            String finalMessage = gameHandler.generateFinalMessage();
            entity.setMessage(finalMessage);
        }

        EntityModel<EventEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

    @PutMapping("/newGame")
    public ResponseEntity<EntityModel<EventEntity>> newGame() {
        EventEntity entity = gameHandler.startNewGame();

        EntityModel<EventEntity> entityModel = assembler.toModel(repository.save(entity));

        return ResponseEntity.ok(entityModel);
    }

}