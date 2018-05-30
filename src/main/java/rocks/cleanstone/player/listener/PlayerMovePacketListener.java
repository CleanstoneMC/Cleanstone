package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public class PlayerMovePacketListener {

    private final PlayerManager playerManager;

    public PlayerMovePacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof PlayerLookPacket)) {
            return;
        }

        PlayerLookPacket playerLookPacket = (PlayerLookPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        Entity entity = player.getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Rotation newRotation = new Rotation(oldRotation);

        newRotation.setPitch(playerLookPacket.getPitch());
        newRotation.setYaw(playerLookPacket.getYaw());

        entity.getLocation().setRotation(newRotation);

        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, oldPosition, newRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof PlayerPositionPacket)) {
            return;
        }

        PlayerPositionPacket playerPositionPacket = (PlayerPositionPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        Human entity = player.getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getLocation().getPosition();
        Rotation oldRotation = entity.getLocation().getRotation();
        Position newPosition = new Position(oldPosition);

        newPosition.setX(playerPositionPacket.getX());
        newPosition.setY(playerPositionPacket.getFeetY());
        newPosition.setZ(playerPositionPacket.getZ());

        entity.getLocation().setPosition(newPosition);

        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, newPosition, oldRotation));
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerPositionAndLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof InPlayerPositionAndLookPacket)) {
            return;
        }
        InPlayerPositionAndLookPacket playerPositionAndLookPacket = (InPlayerPositionAndLookPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        Entity entity = player.getEntity();

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

        entity.getLocation().setPosition(newPosition);
        entity.getLocation().setRotation(newRotation);

        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, newPosition, newRotation));
    }
}
