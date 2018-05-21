package rocks.cleanstone.game.entity;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class EntityMovePacketListener {

    private final PlayerManager playerManager;
    private final EntityManager entityManager;

    public EntityMovePacketListener(PlayerManager playerManager, EntityManager entityManager) {
        this.playerManager = playerManager;
        this.entityManager = entityManager;
    }

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

        Position oldPosition = entity.getPosition();
        Rotation oldRotation = entity.getRotation();
        Position newPosition = new Position(oldPosition);
        Rotation newRotation = new Rotation(oldRotation);

        newRotation.setPitch(playerLookPacket.getPitch());
        newRotation.setYaw(playerLookPacket.getYaw());

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, oldRotation, newPosition, newRotation));
        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, newPosition, newRotation));
    }

    @EventListener
    public void onPlayerPositionPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof PlayerPositionPacket)) {
            return;
        }

        PlayerPositionPacket playerPositionPacket = (PlayerPositionPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        Entity entity = player.getEntity();

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getPosition();
        Rotation oldRotation = entity.getRotation();
        Position newPosition = new Position(oldPosition);
        Rotation newRotation = new Rotation(oldRotation);

        newPosition.setX(playerPositionPacket.getX());
        newPosition.setY(playerPositionPacket.getFeetY());
        newPosition.setZ(playerPositionPacket.getZ());

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, oldRotation, newPosition, newRotation));
        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, newPosition, newRotation));
    }

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

        Position oldPosition = entity.getPosition();
        Rotation oldRotation = entity.getRotation();
        Position newPosition = new Position(oldPosition);
        Rotation newRotation = new Rotation(oldRotation);

        newPosition.setX(playerPositionAndLookPacket.getX());
        newPosition.setY(playerPositionAndLookPacket.getY());
        newPosition.setZ(playerPositionAndLookPacket.getZ());

        newRotation.setPitch(playerPositionAndLookPacket.getPitch());
        newRotation.setYaw(playerPositionAndLookPacket.getYaw());

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, oldRotation, newPosition, newRotation));
        CleanstoneServer.publishEvent(new PlayerMoveEvent(player, oldPosition, oldRotation, newPosition, newRotation));
    }
}
