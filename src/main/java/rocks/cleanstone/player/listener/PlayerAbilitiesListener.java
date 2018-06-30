package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Arrays;

import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class PlayerAbilitiesListener {

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerAbilities(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof InPlayerAbilitiesPacket)) {
            return;
        }
        InPlayerAbilitiesPacket packet = (InPlayerAbilitiesPacket) event.getPacket();
        Player player = event.getPlayer();

        player.setFlying(Arrays.asList(packet.getPlayerAbilities()).contains(PlayerAbility.IS_FLYING));
    }
}
