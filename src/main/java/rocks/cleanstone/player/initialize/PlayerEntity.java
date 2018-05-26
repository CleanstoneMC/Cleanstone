package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.SimpleGeneratedWorld;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class PlayerEntity {

    private final EntityManager entityManager;

    public PlayerEntity(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Order(value = 10)
    @EventListener
    public void onJoin(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        Position position = new Position(0, 46, 0, new SimpleGeneratedWorld("", null, null));
        Rotation rotation = new Rotation(0, 0);

        Human human = new SimpleHuman(position, rotation);
        human = entityManager.addEntityWithoutID(human);

        player.setEntity(human);
    }
}
