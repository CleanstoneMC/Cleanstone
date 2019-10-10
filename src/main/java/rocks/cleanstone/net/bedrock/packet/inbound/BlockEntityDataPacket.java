package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.BlockCoordinates;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockEntityDataPacket implements Packet {

    private final BlockCoordinates coordinates;
    private final NamedBinaryTag namedTag;

    public BlockEntityDataPacket(BlockCoordinates coordinates, NamedBinaryTag namedTag) {
        this.coordinates = coordinates;
        this.namedTag = namedTag;
    }

    public BlockCoordinates getCoordinates() {
        return coordinates;
    }

    public NamedBinaryTag getNamedTag() {
        return namedTag;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.BLOCK_ENTITY_DATA;
    }
}

