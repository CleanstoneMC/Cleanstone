package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import rocks.cleanstone.endpoint.minecraft.vanilla.legacy.state.mapping.IDMetadataPair;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EntityMetadataPacket;
import rocks.cleanstone.game.entity.types.Item;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.item.mapping.ItemTypeMapping;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class EntityMetadataCodec implements OutboundPacketCodec<EntityMetadataPacket> {

    private final ItemTypeMapping<Integer> itemTypeMapping;

    public EntityMetadataCodec(@Qualifier("protocolItemTypeMapping_v1_12_2") ItemTypeMapping<Integer> itemTypeMapping) {
        this.itemTypeMapping = itemTypeMapping;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityMetadataPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());

        if (packet.getEntity() instanceof Item) {
            byteBuf.writeByte(6);
            byteBuf.writeByte(5);
            serializeSlot(byteBuf, ((Item) packet.getEntity()).getItemStack());
        }

        byteBuf.writeByte(0xff);

        return byteBuf;
    }

    public void serializeSlot(ByteBuf byteBuf, ItemStack itemStack) {
        if (itemStack.getAmount() == 0) {
            byteBuf.writeShort(-1);

            return;
        }

        IDMetadataPair pair = IDMetadataPair.decode(itemTypeMapping.getID(itemStack.getType()));
        byteBuf.writeShort(pair.getID());
        byteBuf.writeByte(itemStack.getAmount());
        byteBuf.writeShort(pair.getMetadata()); // Durability
        byteBuf.writeByte(0); // Metadata

    }
}
