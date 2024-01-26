package rpg.potato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rpg.potato.models.GameStateEntity;

@Repository
public interface GameStateRepository extends JpaRepository<GameStateEntity, Long> {

}
