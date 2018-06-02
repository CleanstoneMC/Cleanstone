package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
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

        Entity entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Rotation newRotation = new Rotation(oldRotation);

        newRotation.setPitch(playerLookPacket.getPitch());
        newRotation.setYaw(playerLookPacket.getYaw());

        entity.setLocation(new Location(oldPosition, newRotation));

        CleanstoneServer.publishEvent(new PlayerMoveEvent(
                event.getPlayer(), oldPosition, oldRotation, oldPosition, newRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof PlayerPositionPacket)) {
            return;
        }

        PlayerPositionPacket playerPositionPacket = (PlayerPositionPacket) event.getPacket();

        Human entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Position newPosition = new Position(oldPosition);

        newPosition.setX(playerPositionPacket.getX());
        newPosition.setY(playerPositionPacket.getFeetY());
        newPosition.setZ(playerPositionPacket.getZ());

        entity.setLocation(new Location(newPosition, oldRotation));

        CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, oldRotation, newPosition, oldRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionAndLookPacket(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof InPlayerPositionAndLookPacket)) {
            return;
        }
        InPlayerPositionAndLookPacket playerPositionAndLookPacket = (InPlayerPositionAndLookPacket) event.getPacket();

        Entity entity = event.getPlayer().getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Position newPosition = new Position(oldPosition);
        Rotation newRotation = new Rotation(oldRotation);

        newPosition.setX(playerPositionAndLookPacket.getX());
        newPosition.setY(playerPositionAndLookPacket.getY());
        newPosition.setZ(playerPositionAndLookPacket.getZ());

        newRotation.setPitch(playerPositionAndLookPacket.getPitch());
        newRotation.setYaw(playerPositionAndLookPacket.getYaw());

        entity.setLocation(new Location(newPosition, newRotation));

        CleanstoneServer.publishEvent(
                new PlayerMoveEvent(event.getPlayer(), oldPosition, oldRotation, newPosition, newRotation));
    }
}
