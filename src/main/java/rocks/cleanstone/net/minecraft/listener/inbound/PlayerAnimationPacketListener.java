package rocks.cleanstone.net.minecraft.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InAnimationPacket;
import rocks.cleanstone.player.event.PlayerAnimationEvent;

@Component
public class PlayerAnimationPacketListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<InAnimationPacket> playerInboundPacketEvent) {
        PlayerAnimationEvent playerAnimationEvent = new PlayerAnimationEvent(
                playerInboundPacketEvent.getPlayer(), playerInboundPacketEvent.getPacket().getHand());

        CleanstoneServer.publishEvent(playerAnimationEvent);
    }
}
