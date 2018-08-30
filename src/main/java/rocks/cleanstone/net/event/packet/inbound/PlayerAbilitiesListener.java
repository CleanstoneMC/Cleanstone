package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.player.Player;

import java.util.Arrays;

@Component
public class PlayerAbilitiesListener extends PlayerInboundPacketEventListener<InPlayerAbilitiesPacket> {

    @Async(value = "playerExec")
    @EventListener
    @Override
    public void onPacket(PlayerInboundPacketEvent<InPlayerAbilitiesPacket> playerInboundPacketEvent) {
        InPlayerAbilitiesPacket packet = playerInboundPacketEvent.getPacket();
        Player player = playerInboundPacketEvent.getPlayer();

        player.setFlying(Arrays.asList(packet.getPlayerAbilities()).contains(PlayerAbility.IS_FLYING));
    }
}
