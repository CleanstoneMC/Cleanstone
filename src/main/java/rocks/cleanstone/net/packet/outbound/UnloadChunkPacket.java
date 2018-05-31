package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UnloadChunkPacket implements Packet {

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
