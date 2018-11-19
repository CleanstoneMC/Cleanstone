package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateBlockSyncedPacket implements Packet {

    private final BlockCoordinates coordinates;
    private final int blockRuntimeID;
    private final int blockPriority;
    private final int dataLayerID;
    private final long unknown0;
    private final long unknown1;

    public UpdateBlockSyncedPacket(BlockCoordinates coordinates, int blockRuntimeID, int blockPriority, int dataLayerID, long unknown0, long unknown1) {
        this.coordinates = coordinates;
        this.blockRuntimeID = blockRuntimeID;
        this.blockPriority = blockPriority;
        this.dataLayerID = dataLayerID;
        this.unknown0 = unknown0;
        this.unknown1 = unknown1;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public int getBlockRuntimeID() {
        return blockRuntimeID;
    }

    public int getBlockPriority() {
        return blockPriority;
    }

    public int getDataLayerID() {
        return dataLayerID;
    }

    public long getUnknown0() {
        return unknown0;
    }

    public long getUnknown1() {
        return unknown1;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.UPDATE_BLOCK_SYNCED;
    }
}

