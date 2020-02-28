package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.HeldItemChangePacket;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;

@Component
public class HeldItemChangeListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<HeldItemChangePacket> playerInboundPacketEvent) {
        HeldItemChangePacket heldItemChangePacket = playerInboundPacketEvent.getPacket();

        playerInboundPacketEvent.getPlayer().getEntity().setSelectedSlot(heldItemChangePacket.getSlot());
    }
}
