package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.packet.inbound.HeldItemChangePacket;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class HeldItemChangeListener {

    @Async(value = "playerExec")
    @EventListener
    public void onCreativeInventoryAction(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof HeldItemChangePacket)) {
            return;
        }

        HeldItemChangePacket heldItemChangePacket = (HeldItemChangePacket) event.getPacket();

        event.getPlayer().getEntity().setSelectedSlot(heldItemChangePacket.getSlot());
    }
}
