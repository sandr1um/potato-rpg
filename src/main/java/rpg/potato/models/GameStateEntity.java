package rpg.potato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class GameStateEntity {
    private @Id
    @GeneratedValue Long id;
    private int destiny;
    private int potatoes;
    private int orcs;
    private int scaling;
    private String message;

    public GameStateEntity(int destiny, int potatoes, int orcs, int scaling, String message) {
        this.destiny = destiny;
        this.potatoes = potatoes;
        this.orcs = orcs;
        this.scaling = scaling;
        this.message = message;
    }

}
