package rocks.cleanstone.net.minecraft.status;

import com.google.gson.Gson;
import org.springframework.context.event.EventListener;
import rocks.cleanstone.core.player.PlayerManager;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;
import rocks.cleanstone.net.minecraft.packet.inbound.RequestPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.ResponsePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public class StatusRequestListener {

    private final String motd;
    private final int maxPlayers;
    private final PlayerManager playerManager;

    public StatusRequestListener(MinecraftNetworking minecraftNetworking, PlayerManager playerManager) {
        this.motd = minecraftNetworking.getMinecraftConfig().getMotd();
        this.maxPlayers = minecraftNetworking.getMinecraftConfig().getMaxPlayers();
        this.playerManager = playerManager;
    }

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof RequestPacket) {
            ClientProtocolLayer latestSupportedClientVersion = MinecraftClientProtocolLayer.getLatest();

            StatusResponse statusResponse = new StatusResponse(
                    new StatusResponse.Version(latestSupportedClientVersion.getName(),
                            latestSupportedClientVersion.getOrderedVersionNumber()),
                    new StatusResponse.Players(maxPlayers, playerManager.getOnlinePlayers().size(),
                            new StatusResponse.Players.SampleItem[]{}),
                    new StatusResponse.Description(motd));
            String jsonResponse = new Gson().toJson(statusResponse);

            event.getConnection().sendPacket(new ResponsePacket(jsonResponse));
        }
    }
}
