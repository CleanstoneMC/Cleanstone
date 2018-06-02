package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.LivingEntity;
import rocks.cleanstone.game.entity.Location;
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

        PlayerLookPacket playerLookPacket = (PlayerLookPacket) event.getPacket();

        LivingEntity entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Rotation oldHeadRotation = entity.getHeadRotation();

        Rotation newHeadRotation = new Rotation(oldHeadRotation);
        newHeadRotation.setPitch(playerLookPacket.getPitch());
        newHeadRotation.setYaw(playerLookPacket.getYaw());

        boolean adjustBodyRotation = Math.abs(Math.abs(oldRotation.getIntYaw())
                - Math.abs(newHeadRotation.getIntYaw())) > 35;
        Rotation newRotation = adjustBodyRotation ? new Rotation(newHeadRotation) : oldRotation;

        entity.setLocation(new Location(oldPosition, newRotation));
        entity.setHeadRotation(newHeadRotation);

        CleanstoneServer.publishEvent(new PlayerMoveEvent(
                event.getPlayer(), oldPosition, oldRotation, oldHeadRotation, oldPosition,
                newRotation, newHeadRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerPositionPacket)) {
            return;
        }

        PlayerPositionPacket playerPositionPacket = (PlayerPositionPacket) event.getPacket();

        LivingEntity entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Position newPosition = new Position(oldPosition);
        Rotation oldHeadRotation = entity.getHeadRotation();

        newPosition.setX(playerPositionPacket.getX());
        newPosition.setY(playerPositionPacket.getFeetY());
        newPosition.setZ(playerPositionPacket.getZ());

        entity.setLocation(new Location(newPosition, oldRotation));

        CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, oldRotation, oldHeadRotation, newPosition,
                        oldRotation, oldHeadRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionAndLookPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof InPlayerPositionAndLookPacket)) {
            return;
        }
        InPlayerPositionAndLookPacket playerPositionAndLookPacket = (InPlayerPositionAndLookPacket) event.getPacket();

        LivingEntity entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Rotation oldHeadRotation = entity.getHeadRotation();
        Position newPosition = new Position(oldPosition);
        Rotation newRotation = new Rotation(oldRotation);

        Rotation newHeadRotation = new Rotation(newRotation);

        newPosition.setX(playerPositionAndLookPacket.getX());
        newPosition.setY(playerPositionAndLookPacket.getY());
        newPosition.setZ(playerPositionAndLookPacket.getZ());

        newRotation.setPitch(playerPositionAndLookPacket.getPitch());
        newRotation.setYaw(playerPositionAndLookPacket.getYaw());

        entity.setLocation(new Location(newPosition, newRotation));
        entity.setHeadRotation(newHeadRotation);

        CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, oldRotation, oldHeadRotation, newPosition,
                        newRotation, newHeadRotation));
    }
}
