package rocks.cleanstone.net.minecraft.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.StandardMoveReason;

@Component
public class PlayerMovePacketListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerLookPacket(PlayerInboundPacketEvent<PlayerLookPacket> event) {
        final PlayerLookPacket packet = event.getPacket();
        final LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        final HeadRotatablePosition oldPosition = entity.getPosition();
        final HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.getHeadRotation().setPitch(packet.getPitch());
        newPosition.getHeadRotation().setYaw(packet.getYaw());

        float rotationDiff = oldPosition.getHeadRotation().getYaw() - newPosition.getHeadRotation().getYaw();
        rotationDiff = (Math.abs(rotationDiff) + 180) % 360 - 180;
        final boolean adjustBodyRotation = Math.abs(rotationDiff) > 35;

        if (adjustBodyRotation) {
            newPosition.setRotation(new Rotation(newPosition.getHeadRotation()));
        }

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionPacket(PlayerInboundPacketEvent<PlayerPositionPacket> event) {
        final PlayerPositionPacket packet = event.getPacket();

        final LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        final HeadRotatablePosition oldPosition = entity.getPosition();
        final HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.setX(packet.getX());
        newPosition.setY(packet.getFeetY());
        newPosition.setZ(packet.getZ());

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionAndLookPacket(PlayerInboundPacketEvent<InPlayerPositionAndLookPacket> event) {
        final InPlayerPositionAndLookPacket packet = event.getPacket();

        final LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        final HeadRotatablePosition oldPosition = entity.getPosition();
        final HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.setX(packet.getX());
        newPosition.setY(packet.getY());
        newPosition.setZ(packet.getZ());

        newPosition.getRotation().setPitch(packet.getPitch());
        newPosition.getRotation().setYaw(packet.getYaw());

        newPosition.setHeadRotation(newPosition.getRotation());

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }
}
