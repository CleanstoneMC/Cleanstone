package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class UnloadChunkPacket extends OutboundPacket {

    private final int chunkX;
    private final int chunkY;

    public UnloadChunkPacket(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.UNLOAD_CHUNK;
    }
}
