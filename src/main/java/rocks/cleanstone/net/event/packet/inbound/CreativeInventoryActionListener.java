package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.CreativeInventoryActionPacket;

@Component
public class CreativeInventoryActionListener extends PlayerInboundPacketEventListener<CreativeInventoryActionPacket> {

    @Async(value = "playerExec")
    @EventListener
    @Override
    public void onPacket(PlayerInboundPacketEvent<CreativeInventoryActionPacket> playerInboundPacketEvent) {
        CreativeInventoryActionPacket packet = playerInboundPacketEvent.getPacket();

        playerInboundPacketEvent.getPlayer().getEntity().getInventory().setItemInSlot(packet.getSlot(), packet.getClickedItem());
    }
}
