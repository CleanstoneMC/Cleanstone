package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.CHUNK_RADIUS_UPDATE;
    }
}

