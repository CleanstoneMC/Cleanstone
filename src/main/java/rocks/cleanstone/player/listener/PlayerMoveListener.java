package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.EntityMoveEvent;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class PlayerMoveListener {
    private final PlayerManager playerManager;

    public PlayerMoveListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    public void onPlayerMove(EntityMoveEvent entityMoveEvent) {
        if (!(entityMoveEvent.getEntity() instanceof Player)) {
            return;
        }

        Position oldPosition = entityMoveEvent.getOldPosition();
        Position newPosition = entityMoveEvent.getNewPosition();

        final int entityID = entityMoveEvent.getEntity().getEntityID();

        final short deltaX = (short) ((newPosition.getX() * 32 - oldPosition.getX() * 32) * 128);
        final short deltaY = (short) ((newPosition.getY() * 32 - oldPosition.getY() * 32) * 128);
        final short deltaZ = (short) ((newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128);

        final float pitch = entityMoveEvent.getNewRotation().getPitch();
        final float yaw = entityMoveEvent.getNewRotation().getYaw();

        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = new EntityLookAndRelativeMovePacket(entityID, deltaX, deltaY, deltaZ, yaw, pitch, true); //TODO: Add onGround
        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(entityID, ((Player) entityMoveEvent.getEntity()).getId().getUUID(), newPosition.getX(), newPosition.getY(), newPosition.getZ(), yaw, pitch, null); //TODO: Add Metadata

        playerManager.getOnlinePlayers().forEach(player -> {
            if (player.getEntity().getEntityID() == entityLookAndRelativeMovePacket.getEntityID()) {
                return; //TODO: Should we skip the causing player?
            }

            if (isPositionInReach(newPosition, player.getEntity().getPosition(), 150)) {
                if (!isPositionInReach(oldPosition, player.getEntity().getPosition(), 150)) {
                    player.sendPacket(spawnPlayerPacket);
                }

                player.sendPacket(entityLookAndRelativeMovePacket);
            }
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

        if (position1.getZ() + max < position2.getZ() || position1.getZ() - max > position2.getZ()) {
            return false;
        }

        return true;
    }
}
