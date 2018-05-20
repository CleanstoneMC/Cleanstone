package rocks.cleanstone.game.entity;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.player.PlayerManager;

public class EntityMovePacketListener {

    private final EntityManager entityManager;

    public EntityMovePacketListener(PlayerManager playerManager, EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @EventListener
    public void onEntityLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityLookPacket)) {
            return;
        }

        EntityLookPacket entityLookPacket = (EntityLookPacket) inboundPacketEvent.getPacket();

        Entity entity = entityManager.getEntityByID(entityLookPacket.getEntityID());

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getPosition();
        Position newPosition = new Position(oldPosition);

        //TODO: Set Direction in newPosition

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, newPosition));
    }

    @EventListener
    public void onEntityMovePacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityRelativeMovePacket)) {
            return;
        }

        EntityRelativeMovePacket entityRelativeMovePacket = (EntityRelativeMovePacket) inboundPacketEvent.getPacket();

        Entity entity = entityManager.getEntityByID(entityRelativeMovePacket.getEntityID());

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getPosition();
        Position newPosition = new Position(oldPosition);

        final double x = newPosition.getX() + entityRelativeMovePacket.getDeltaX();
        final double y = newPosition.getY() + entityRelativeMovePacket.getDeltaY();
        final double z = newPosition.getZ() + entityRelativeMovePacket.getDeltaZ();

        newPosition.setX(x);
        newPosition.setY(y);
        newPosition.setZ(z);

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, newPosition));
    }

    @EventListener
    public void onEntityMoveAndLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityLookAndRelativeMovePacket)) {
            return;
        }
        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = (EntityLookAndRelativeMovePacket) inboundPacketEvent.getPacket();

        Entity entity = entityManager.getEntityByID(entityLookAndRelativeMovePacket.getEntityID());

        if (entity == null) {
            return;
        }

        Position oldPosition = entity.getPosition();
        Position newPosition = new Position(oldPosition);

        //TODO: Set Direction in newPosition

        final double x = newPosition.getX() + entityLookAndRelativeMovePacket.getDeltaX();
        final double y = newPosition.getY() + entityLookAndRelativeMovePacket.getDeltaY();
        final double z = newPosition.getZ() + entityLookAndRelativeMovePacket.getDeltaZ();

        newPosition.setX(x);
        newPosition.setY(y);
        newPosition.setZ(z);

        CleanstoneServer.publishEvent(new EntityMoveEvent(entity, oldPosition, newPosition));
    }
}
