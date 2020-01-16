package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TransferPacket implements Packet {

    private final String serverAddress;
    private final short port;

    public TransferPacket(String serverAddress, short port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public short getPort() {
        return port;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.TRANSFER;
    }
}

