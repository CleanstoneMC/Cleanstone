package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.player.Player;

@Component
public class ClientSettingsListener extends PlayerInboundPacketEventListener<ClientSettingsPacket> {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public ClientSettingsListener(MinecraftConfig minecraftConfig) {
        this.minecraftConfig = minecraftConfig;
    }

    @Async(value = "playerExec")
    @EventListener
    @Override
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
