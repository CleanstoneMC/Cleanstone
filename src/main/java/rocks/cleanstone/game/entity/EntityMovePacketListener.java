package rocks.cleanstone.game.entity;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.player.PlayerManager;

public class EntityMovePacketListener {

    private final PlayerManager playerManager;

    public EntityMovePacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    public void onEntityLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityLookPacket)) {
            return;
        }

        EntityLookPacket entityLookPacket = (EntityLookPacket) inboundPacketEvent.getPacket();

        Entity entity = null; //TODO: Get Correct Entity by ID


        CleanstoneServer.publishEvent(new EntityMoveEvent(null, null, null));
    }

    @EventListener
    public void onEntityMovePacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityRelativeMovePacket)) {
            return;
        }

        EntityRelativeMovePacket entityRelativeMovePacket = (EntityRelativeMovePacket) inboundPacketEvent.getPacket();

        Entity entity = null; //TODO: Get Correct Entity by ID

        CleanstoneServer.publishEvent(new EntityMoveEvent(null, null, null));
    }

    @EventListener
    public void onEntityMoveAndLookPacket(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof EntityLookAndRelativeMovePacket)) {
            return;
        }
        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = (EntityLookAndRelativeMovePacket) inboundPacketEvent.getPacket();

        Entity entity = null; //TODO: Get Correct Entity by ID

        CleanstoneServer.publishEvent(new EntityMoveEvent(null, null, null));
    }

}
