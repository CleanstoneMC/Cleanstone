package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class CreativeInventoryActionCodec implements PacketCodec {

    private final MaterialRegistry materialRegistry;

    public CreativeInventoryActionCodec(MaterialRegistry materialRegistry) {
        this.materialRegistry = materialRegistry;
    }

    @Override
    public Packet decode(ByteBuf byteBuf) {
        short slot = byteBuf.readShort();
        ItemStack clickedItem = ByteBufUtils.readItemStack(byteBuf, materialRegistry);

        return new CreativeInventoryActionPacket(slot, clickedItem);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("CreativeInventoryAction is inbound and cannot be encoded");
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
