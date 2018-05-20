package rocks.cleanstone.game.entity;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.player.PlayerManager;

import java.util.HashMap;
import java.util.Map;

public class EntityMoveListener {
    private final PlayerManager playerManager;
    private static final Map<EntityType, Integer> maxDistance = new HashMap<>();

    public EntityMoveListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    public void onEntityMove(EntityMoveEvent entityMoveEvent) {
        Position oldPosition = entityMoveEvent.getOldPosition();
        Position newPosition = entityMoveEvent.getNewPosition();

        final int entityID = entityMoveEvent.getEntity().getEntityID();

        final short deltaX = (short) ((newPosition.getX() * 32 - oldPosition.getX() * 32) * 128);
        final short deltaY = (short) ((newPosition.getY() * 32 - oldPosition.getY() * 32) * 128);
        final short deltaZ = (short) ((newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128);

        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = new EntityLookAndRelativeMovePacket(entityID, deltaX, deltaY, deltaZ, 0, 0, true); //TODO: Add Pitch, Yaw and onGround

        playerManager.getOnlinePlayers().forEach(player -> {
            if (player.getEntity().getEntityID() == entityLookAndRelativeMovePacket.getEntityID()) {
               // return; //TODO: Should we skip the causing player?
            }

            player.sendPacket(entityLookAndRelativeMovePacket);
        });
    }

    private boolean isPositionInReach(Position position1, Position position2, int max) {
        if (!position1.getWorld().equals(position2.getWorld())) {
            return false;
        }

        if (position1.getX() + max < position2.getX() || position1.getX() - max > position2.getX()) {
            return false;
        }

        if (position1.getY() + max < position2.getY() || position1.getY() - max > position2.getY()) {
            return false;
        }

        if (position1.getY() + max < position2.getY() || position1.getY() - max > position2.getY()) {
            return false;
        }

        return true;
    }
}
