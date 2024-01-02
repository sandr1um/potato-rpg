package rpg.potato.event;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<EventEntity, EntityModel<EventEntity>> {
    @Override
    public EntityModel<EventEntity> toModel(EventEntity entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(EventController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("events"));
    }
}