package rocks.cleanstone.player.event;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
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
        HeadRotatablePosition oldPosition = getOldPosition();
        HeadRotatablePosition newPosition = getNewPosition();
        int entityID = player.getEntity().getEntityID();
        float yaw = newPosition.getRotation().getYaw();
        float pitch = newPosition.getRotation().getPitch();
        EntityTeleportPacket entityTeleportPacket = new EntityTeleportPacket(entityID, oldPosition.getX(),
                oldPosition.getY(), oldPosition.getZ(), yaw, pitch, player.isFlying());

        player.sendPacket(entityTeleportPacket);
        super.cancel();
    }
}
