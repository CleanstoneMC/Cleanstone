package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UnloadChunkPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;

    public UnloadChunkPacket(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getchunkZ() {
        return chunkZ;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.UNLOAD_CHUNK;
    }
}
