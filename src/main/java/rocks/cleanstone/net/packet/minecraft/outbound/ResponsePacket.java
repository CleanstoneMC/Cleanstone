package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class ResponsePacket extends OutboundPacket {

    private final String jsonResponse;

    public ResponsePacket(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public String getJSONResponse() {
        return jsonResponse;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.RESPONSE;
    }
}
