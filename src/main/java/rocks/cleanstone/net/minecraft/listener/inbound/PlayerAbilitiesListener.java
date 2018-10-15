package rocks.cleanstone.net.minecraft.listener.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.player.Player;

import java.util.Arrays;

@Component
public class PlayerAbilitiesListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<InPlayerAbilitiesPacket> playerInboundPacketEvent) {
        final InPlayerAbilitiesPacket packet = playerInboundPacketEvent.getPacket();
        final Player player = playerInboundPacketEvent.getPlayer();

        player.setFlying(Arrays.asList(packet.getPlayerAbilities()).contains(PlayerAbility.IS_FLYING));
    }
}
