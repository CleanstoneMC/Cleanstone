package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class FullChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final byte[] chunkData;

    public FullChunkDataPacket(int chunkX, int chunkZ, byte[] chunkData) {
        this.chunkX =  chunkX;
        this.chunkZ =  chunkZ;
        this.chunkData =  chunkData;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public byte[] getChunkData() {
        return chunkData;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.FULL_CHUNK_DATA;
    }
}

