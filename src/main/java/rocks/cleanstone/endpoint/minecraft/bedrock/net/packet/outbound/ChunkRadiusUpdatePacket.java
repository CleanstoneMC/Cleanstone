package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChunkRadiusUpdatePacket implements Packet {

    private final int chunkRadius;

    public ChunkRadiusUpdatePacket(int chunkRadius) {
        this.chunkRadius = chunkRadius;
    }

    public int getChunkRadius() {
        return chunkRadius;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CHUNK_RADIUS_UPDATE;
    }
}

