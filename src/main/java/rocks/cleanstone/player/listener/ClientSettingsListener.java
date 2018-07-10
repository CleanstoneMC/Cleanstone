package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;

public class ClientSettingsListener {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public ClientSettingsListener(MinecraftConfig minecraftConfig) {
        this.minecraftConfig = minecraftConfig;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onClientSettingsReceive(PlayerInboundPacketEvent event) {
        if (!(event.getPacket() instanceof ClientSettingsPacket)) {
            return;
        }
        Player player = event.getPlayer();
        ClientSettingsPacket packet = (ClientSettingsPacket) event.getPacket();

        int viewDistance = Math.min(packet.getViewDistance(), minecraftConfig.getMaxViewDistance());
        player.setViewDistance(viewDistance);

        player.setDisplayedSkinParts(packet.getDisplayedSkinParts());
        player.setMainHandSide(packet.getMainHandSide());
        player.setChatMode(packet.getChatMode());
        player.setLocale(packet.getLocale());
    }
}
