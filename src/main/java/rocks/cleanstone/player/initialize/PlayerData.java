package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class PlayerData {

    private final OpenWorldGame openWorldGame;
    private final EntityManager entityManager;

    public PlayerData(OpenWorldGame openWorldGame, EntityManager entityManager) {
        this.openWorldGame = openWorldGame;
        this.entityManager = entityManager;
    }

    @Order(value = 5)
    @EventListener
    public void onJoin(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        // TODO Get player data from data source

        Location spawnLocation;
        try {
            spawnLocation = openWorldGame.getFirstSpawnWorld().getFirstSpawnLocation();
        } catch (NullPointerException er) {
            spawnLocation = new Location(new Position(0, 46, 0, null), new Rotation(0, 0));
        }
        Rotation spawnHeadRotation = new Rotation(spawnLocation.getRotation());

        Human human = new SimpleHuman(spawnLocation, spawnHeadRotation);
        human = entityManager.addEntityWithoutID(human);

        player.setEntity(human);
    }
}
