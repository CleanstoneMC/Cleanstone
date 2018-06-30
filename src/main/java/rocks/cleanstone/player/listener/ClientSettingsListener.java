package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class ClientSettingsListener {

    @Async(value = "playerExec")
    @EventListener
    public void onClientSettingsReceive(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof ClientSettingsPacket)) {
            return;
        }
        Player player = event.getPlayer();
        ClientSettingsPacket packet = (ClientSettingsPacket) event.getPacket();
        player.setChatMode(packet.getChatMode());
        player.setLocale(packet.getLocale());
        player.setViewDistance(packet.getViewDistance());
        player.setDisplayedSkinParts(packet.getDisplayedSkinParts());
        player.setMainHandSide(packet.getMainHandSide());
    }
}
