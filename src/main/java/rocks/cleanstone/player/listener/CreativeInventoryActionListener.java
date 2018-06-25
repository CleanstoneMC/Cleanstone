package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.inventory.item.SimpleItemStack;
import rocks.cleanstone.net.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class CreativeInventoryActionListener {

    @Async(value = "playerExec")
    @EventListener
    public void onCreativeInventoryAction(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof CreativeInventoryActionPacket)) {
            return;
        }

        CreativeInventoryActionPacket creativeInventoryActionPacket = (CreativeInventoryActionPacket) event.getPacket();

        event.getPlayer().getEntity().getInventory().setItemInSlot(creativeInventoryActionPacket.getSlot(), SimpleItemStack.fromSlot(creativeInventoryActionPacket.getClickedItem()));
    }
}
