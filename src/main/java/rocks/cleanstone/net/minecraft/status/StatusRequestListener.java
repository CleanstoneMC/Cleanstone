package rocks.cleanstone.net.minecraft.status;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.RequestPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.ResponsePacket;

public class StatusRequestListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onReceive(InboundPacketEvent event) {
        if (event.getPacket() instanceof RequestPacket) {
            StatusResponse statusResponse = new StatusResponse(new StatusResponse.Version("1.12.2", 340),
                    new StatusResponse.Players(999999999, 0, new StatusResponse.Players.SampleItem[]{}),
                    new StatusResponse.Description("Cleanstone Advanced Minecraft Server"));
            String jsonResponse = new Gson().toJson(statusResponse);
            event.getConnection().sendPacket(new ResponsePacket(jsonResponse));
        }
    }
}
