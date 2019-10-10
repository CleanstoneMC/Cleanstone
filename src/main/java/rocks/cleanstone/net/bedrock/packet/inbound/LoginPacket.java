package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class LoginPacket implements Packet {

    private final int protocolVersion;
    private final byte[] payload;

    public LoginPacket(int protocolVersion, byte[] payload) {
        this.protocolVersion = protocolVersion;
        this.payload = payload;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.LOGIN;
    }
}

