package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;

@Component
public class CreativeInventoryActionListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<CreativeInventoryActionPacket> playerInboundPacketEvent) {
        CreativeInventoryActionPacket packet = playerInboundPacketEvent.getPacket();

        playerInboundPacketEvent.getPlayer().getEntity().getInventory().setItemInSlot(packet.getSlot(), packet.getClickedItem());
    }
}
