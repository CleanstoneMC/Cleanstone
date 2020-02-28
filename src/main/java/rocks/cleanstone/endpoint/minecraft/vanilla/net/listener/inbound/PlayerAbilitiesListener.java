package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.PlayerAbility;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.Player;

import java.util.Arrays;

@Component
public class PlayerAbilitiesListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<InPlayerAbilitiesPacket> playerInboundPacketEvent) {
        InPlayerAbilitiesPacket packet = playerInboundPacketEvent.getPacket();
        Player player = playerInboundPacketEvent.getPlayer();

        player.setFlying(Arrays.asList(packet.getPlayerAbilities()).contains(PlayerAbility.IS_FLYING));
    }
}
