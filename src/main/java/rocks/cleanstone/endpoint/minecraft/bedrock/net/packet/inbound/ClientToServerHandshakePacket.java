package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ClientToServerHandshakePacket implements Packet {


    public ClientToServerHandshakePacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}

