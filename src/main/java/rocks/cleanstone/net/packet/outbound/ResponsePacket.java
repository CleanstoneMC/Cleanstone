package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ResponsePacket implements Packet {

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
