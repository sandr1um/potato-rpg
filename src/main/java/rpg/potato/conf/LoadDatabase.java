package rpg.potato.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rpg.potato.models.GameStateEntity;
import rpg.potato.repositories.GameStateRepository;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GameStateRepository repository) {
        return args -> log.info("Preloading " + repository.save(new GameStateEntity(0, 0, 0, 1, "")));
    }
}