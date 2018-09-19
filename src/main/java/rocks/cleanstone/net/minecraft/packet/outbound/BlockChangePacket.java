package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockChangePacket implements Packet {

    private final Position position;
    private final BlockState blockState;

    public BlockChangePacket(Position position, BlockState blockState) {
        this.position = position;
        this.blockState = blockState;
    }

    public Position getPosition() {
        return position;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BLOCK_CHANGE;
    }
}
