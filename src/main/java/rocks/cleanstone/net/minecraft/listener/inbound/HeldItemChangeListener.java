package rocks.cleanstone.net.minecraft.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.HeldItemChangePacket;

@Component
public class HeldItemChangeListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<HeldItemChangePacket> playerInboundPacketEvent) {
        final HeldItemChangePacket heldItemChangePacket = playerInboundPacketEvent.getPacket();

        playerInboundPacketEvent.getPlayer().getEntity().setSelectedSlot(heldItemChangePacket.getSlot());
    }
}
