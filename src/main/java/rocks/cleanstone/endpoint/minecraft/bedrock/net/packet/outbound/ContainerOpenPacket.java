package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ContainerOpenPacket implements Packet {

    private final byte windowID;
    private final byte packetType;
    private final BlockCoordinates coordinates;
    private final long runtimeEntityID;

    public ContainerOpenPacket(byte windowID, byte packetType, BlockCoordinates coordinates, long runtimeEntityID) {
        this.windowID = windowID;
        this.packetType = packetType;
        this.coordinates = coordinates;
        this.runtimeEntityID = runtimeEntityID;
    }

    public byte getWindowID() {
        return windowID;
    }

    public byte getPacketType() {
        return packetType;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CONTAINER_OPEN;
    }
}

