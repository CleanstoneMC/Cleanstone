package rocks.cleanstone.endpoint.minecraft.java.net.status;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.MinecraftNetworking;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.RequestPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.ResponsePacket;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.player.PlayerManager;

@Component
public class StatusRequestListener {

    private final String motd;
    private final int maxPlayers;
    private final PlayerManager playerManager;

    @Autowired
    public StatusRequestListener(MinecraftNetworking minecraftNetworking, PlayerManager playerManager) {
        this.motd = minecraftNetworking.getMinecraftConfig().getMotd();
        this.maxPlayers = minecraftNetworking.getMinecraftConfig().getMaxPlayers();
        this.playerManager = playerManager;
    }

    @Async
    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof RequestPacket) {
            ClientProtocolLayer latestSupportedClientVersion = event.getConnection().getClientProtocolLayer();

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
