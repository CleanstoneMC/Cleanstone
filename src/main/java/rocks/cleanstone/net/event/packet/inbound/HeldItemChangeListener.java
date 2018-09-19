package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.HeldItemChangePacket;

@Component
public class HeldItemChangeListener extends PlayerInboundPacketEventListener<HeldItemChangePacket> {

    @Async(value = "playerExec")
    @EventListener
    @Override
    public void onPacket(PlayerInboundPacketEvent<HeldItemChangePacket> playerInboundPacketEvent) {
        HeldItemChangePacket heldItemChangePacket = playerInboundPacketEvent.getPacket();

        playerInboundPacketEvent.getPlayer().getEntity().setSelectedSlot(heldItemChangePacket.getSlot());
    }
}
