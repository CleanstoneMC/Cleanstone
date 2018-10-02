package rocks.cleanstone.net.minecraft.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.player.Player;

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
