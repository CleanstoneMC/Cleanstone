package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class CreativeInventoryActionCodec implements PacketCodec {

    public CreativeInventoryActionCodec(BlockStateMapping<Integer> blockStateMapping) {
        BlockStateMapping<Integer> blockStateMapping1 = blockStateMapping;
    }

    @Override
    public Packet decode(ByteBuf byteBuf) {
        short slot = byteBuf.readShort();
        ItemStack clickedItem = ByteBufUtils.readItemStack(byteBuf, null); // TODO

        return new CreativeInventoryActionPacket(slot, clickedItem);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("CreativeInventoryAction is inbound and cannot be encoded");
    }
}
