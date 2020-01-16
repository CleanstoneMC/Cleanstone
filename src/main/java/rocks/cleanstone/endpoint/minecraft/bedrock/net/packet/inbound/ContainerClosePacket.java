package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ContainerClosePacket implements Packet {

    private final byte windowID;

    public ContainerClosePacket(byte windowID) {
        this.windowID = windowID;
    }

    public byte getWindowID() {
        return windowID;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.CONTAINER_CLOSE;
    }
}

