package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
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
