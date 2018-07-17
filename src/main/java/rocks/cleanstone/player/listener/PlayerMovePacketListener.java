package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.net.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public class PlayerMovePacketListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerLookPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerLookPacket)) {
            return;
        }
        PlayerLookPacket packet = (PlayerLookPacket) event.getPacket();
        LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        HeadRotatablePosition oldPosition = entity.getPosition();
        HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.getHeadRotation().setPitch(packet.getPitch());
        newPosition.getHeadRotation().setYaw(packet.getYaw());

        boolean adjustBodyRotation = Math.abs(Math.abs(oldPosition.getRotation().getIntYaw())
                - Math.abs(newPosition.getHeadRotation().getIntYaw())) > 35;
        if (adjustBodyRotation) {
            newPosition.setRotation(new Rotation(newPosition.getHeadRotation()));
        }

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        PlayerMoveEvent.StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerPositionPacket)) {
            return;
        }
        PlayerPositionPacket packet = (PlayerPositionPacket) event.getPacket();

        LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        HeadRotatablePosition oldPosition = entity.getPosition();
        HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.setX(packet.getX());
        newPosition.setY(packet.getFeetY());
        newPosition.setZ(packet.getZ());

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        PlayerMoveEvent.StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionAndLookPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof InPlayerPositionAndLookPacket)) {
            return;
        }
        InPlayerPositionAndLookPacket packet = (InPlayerPositionAndLookPacket) event.getPacket();

        LivingEntity entity = event.getPlayer().getEntity();
        if (entity == null) return;

        HeadRotatablePosition oldPosition = entity.getPosition();
        HeadRotatablePosition newPosition = new HeadRotatablePosition(oldPosition);

        newPosition.setX(packet.getX());
        newPosition.setY(packet.getY());
        newPosition.setZ(packet.getZ());

        newPosition.getRotation().setPitch(packet.getPitch());
        newPosition.getRotation().setYaw(packet.getYaw());

        newPosition.setHeadRotation(newPosition.getRotation());

        if (!CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, newPosition,
                        PlayerMoveEvent.StandardMoveReason.CLIENT_ACTION)).isCancelled()) {
            entity.setPosition(newPosition);
        }
    }
}
