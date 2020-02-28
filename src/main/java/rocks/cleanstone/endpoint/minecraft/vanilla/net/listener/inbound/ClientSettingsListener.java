package rocks.cleanstone.endpoint.minecraft.vanilla.net.listener.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.Player;

@Component
public class ClientSettingsListener {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public ClientSettingsListener(MinecraftConfig minecraftConfig) {
        this.minecraftConfig = minecraftConfig;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<ClientSettingsPacket> playerInboundPacketEvent) {
        Player player = playerInboundPacketEvent.getPlayer();
        ClientSettingsPacket packet = playerInboundPacketEvent.getPacket();

        int viewDistance = Math.min(packet.getViewDistance(), minecraftConfig.getMaxViewDistance());
        player.setViewDistance(viewDistance);

        player.setDisplayedSkinParts(packet.getDisplayedSkinParts());
        player.setMainHandSide(packet.getMainHandSide());
        player.setChatMode(packet.getChatMode());
        player.setLocale(packet.getLocale());
    }
}
