package rocks.cleanstone.player.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.event.EventAction;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerMoveEvent;

@Slf4j
@Component
public class PlayerMoveListener {
    private final PlayerManager playerManager;
    private final PlayerChunkLoadService playerChunkLoadService;

    @Autowired
    public PlayerMoveListener(PlayerManager playerManager, PlayerChunkLoadService playerChunkLoadService) {
        this.playerManager = playerManager;
        this.playerChunkLoadService = playerChunkLoadService;
    }

    @Order(EventAction.PREVENT)
    @EventListener
    public void playerMoveCancellation(PlayerMoveEvent playerMoveEvent) {
        HeadRotatablePosition oldPosition = playerMoveEvent.getOldPosition();
        HeadRotatablePosition newPosition = playerMoveEvent.getNewPosition();

        if (oldPosition.equalCoordinates(newPosition)) {
            return;
        }

        double deltaX = (newPosition.getX() * 32 - oldPosition.getX() * 32) * 128;
        double deltaY = (newPosition.getY() * 32 - oldPosition.getY() * 32) * 128;
        double deltaZ = (newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128;

        if (isTeleport(deltaX, deltaY, deltaZ)) {
            return;
        }

        Player movingPlayer = playerMoveEvent.getPlayer();
        int entityID = movingPlayer.getEntity().getEntityID();
        float yaw = newPosition.getRotation().getYaw();
        float pitch = newPosition.getRotation().getPitch();

        int chunkX = newPosition.getXAsInt() >> 4;
        int chunkZ = newPosition.getZAsInt() >> 4;

        if (!playerChunkLoadService.hasPlayerLoaded(movingPlayer.getID().getUUID(), ChunkCoords.of(chunkX, chunkZ))) {
            playerMoveEvent.cancel();
        }
    }

    private boolean isTeleport(double deltaX, double deltaY, double deltaZ) {
        return deltaX > Short.MAX_VALUE || deltaY > Short.MAX_VALUE || deltaZ > Short.MAX_VALUE
                || deltaX < Short.MIN_VALUE || deltaY < Short.MIN_VALUE || deltaZ < Short.MIN_VALUE;
    }
}
