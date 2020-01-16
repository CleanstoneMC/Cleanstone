package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateViewPositionPacket implements Packet {

    private final ChunkCoords chunkCoords;

    public UpdateViewPositionPacket(ChunkCoords chunkCoords) {
        this.chunkCoords = chunkCoords;
    }

    public ChunkCoords getChunkCoords() {
        return chunkCoords;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.UPDATE_VIEW_POSITION;
    }
}
