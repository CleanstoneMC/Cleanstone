package rocks.cleanstone.player.event;

import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.player.Player;

public class PlayerMoveEvent extends EntityMoveEvent {
    private final Player player;
    private final MoveReason moveReason;

    public PlayerMoveEvent(Player player, HeadRotatablePosition oldPosition,
                           HeadRotatablePosition newPosition, MoveReason moveReason) {
        super(player.getEntity(), oldPosition, newPosition);
        this.player = player;
        this.moveReason = moveReason;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveReason getMoveReason() {
        return moveReason;
    }

    @Override
    public void cancel() {
        final HeadRotatablePosition oldPosition = getOldPosition();
        final HeadRotatablePosition newPosition = getNewPosition();
        final int entityID = player.getEntity().getEntityID();
        final float yaw = newPosition.getRotation().getYaw();
        final float pitch = newPosition.getRotation().getPitch();
        final EntityTeleportPacket entityTeleportPacket = new EntityTeleportPacket(entityID, oldPosition.getX(),
                oldPosition.getY(), oldPosition.getZ(), yaw, pitch, player.isFlying());

        player.sendPacket(entityTeleportPacket);
        super.cancel();
    }
}
