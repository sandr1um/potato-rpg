package rpg.potato.event;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GameStateModelAssembler implements RepresentationModelAssembler<GameStateEntity, EntityModel<GameStateEntity>> {
    @Override
    public EntityModel<GameStateEntity> toModel(GameStateEntity entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(GameStateController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(GameStateController.class).all()).withRel("events"));
    }
}