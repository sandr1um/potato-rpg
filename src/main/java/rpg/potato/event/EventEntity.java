package rpg.potato.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class EventEntity {
    private @Id
    @GeneratedValue Long id;
    private int destiny;
    private int potatoes;
    private int orcs;
    private int scaling;
    private String message;

    public EventEntity(int destiny, int potatoes, int orcs, int scaling, String message) {
        this.destiny = destiny;
        this.potatoes = potatoes;
        this.orcs = orcs;
        this.scaling = scaling;
        this.message = message;
    }

}
