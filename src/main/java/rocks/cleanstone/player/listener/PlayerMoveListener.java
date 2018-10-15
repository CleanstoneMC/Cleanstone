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
        final HeadRotatablePosition oldPosition = playerMoveEvent.getOldPosition();
        final HeadRotatablePosition newPosition = playerMoveEvent.getNewPosition();

        if (oldPosition.equalCoordinates(newPosition)) {
            return;
        }

        final double deltaX = (newPosition.getX() * 32 - oldPosition.getX() * 32) * 128;
        final double deltaY = (newPosition.getY() * 32 - oldPosition.getY() * 32) * 128;
        final double deltaZ = (newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128;

        if (isTeleport(deltaX, deltaY, deltaZ)) {
            return;
        }

        final Player movingPlayer = playerMoveEvent.getPlayer();
        final int entityID = movingPlayer.getEntity().getEntityID();
        final float yaw = newPosition.getRotation().getYaw();
        final float pitch = newPosition.getRotation().getPitch();

        final int chunkX = newPosition.getXAsInt() >> 4;
        final int chunkZ = newPosition.getZAsInt() >> 4;

        if (!playerChunkLoadService.hasPlayerLoaded(movingPlayer.getID().getUUID(), ChunkCoords.of(chunkX, chunkZ))) {
            playerMoveEvent.cancel();
        }
    }

    private boolean isTeleport(double deltaX, double deltaY, double deltaZ) {
        return deltaX > Short.MAX_VALUE || deltaY > Short.MAX_VALUE || deltaZ > Short.MAX_VALUE
                || deltaX < Short.MIN_VALUE || deltaY < Short.MIN_VALUE || deltaZ < Short.MIN_VALUE;
    }
}
