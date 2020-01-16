package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ContainerSetDataPacket implements Packet {

    private final byte windowID;
    private final int property;
    private final int value;

    public ContainerSetDataPacket(byte windowID, int property, int value) {
        this.windowID = windowID;
        this.property = property;
        this.value = value;
    }

    public byte getWindowID() {
        return windowID;
    }

    public int getProperty() {
        return property;
    }

    public int getValue() {
        return value;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CONTAINER_SET_DATA;
    }
}

