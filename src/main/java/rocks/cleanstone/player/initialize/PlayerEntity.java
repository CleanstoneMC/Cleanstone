package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class PlayerEntity {

    private final OpenWorldGame openWorldGame;
    private final EntityManager entityManager;

    public PlayerEntity(OpenWorldGame openWorldGame, EntityManager entityManager) {
        this.openWorldGame = openWorldGame;
        this.entityManager = entityManager;
    }

    @Order(value = 10)
    @EventListener
    public void onJoin(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        Location spawnLocation = openWorldGame.getFirstSpawnWorld().getFirstSpawnLocation();

        Human human = new SimpleHuman(spawnLocation);
        human = entityManager.addEntityWithoutID(human);

        player.setEntity(human);
    }
}
