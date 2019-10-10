package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerToClientHandshakePacket implements Packet {

    private final String token;

    public ServerToClientHandshakePacket(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SERVER_TO_CLIENT_HANDSHAKE;
    }
}

