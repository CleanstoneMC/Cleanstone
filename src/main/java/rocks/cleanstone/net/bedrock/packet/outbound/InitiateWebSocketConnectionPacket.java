package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InitiateWebSocketConnectionPacket implements Packet {

    private final String server;

    public InitiateWebSocketConnectionPacket(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.INITIATE_WEB_SOCKET_CONNECTION;
    }
}

