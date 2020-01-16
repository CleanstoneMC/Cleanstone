package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.BlockCoordinates;
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

