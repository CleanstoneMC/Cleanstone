package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.LegacyMaterialMapping;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.inventory.item.SimpleItemStack;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.net.minecraft.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.ProtocolItemTypeMapping;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

import javax.annotation.Nullable;

@Component
public class CreativeInventoryActionCodec implements InboundPacketCodec<CreativeInventoryActionPacket> {

    private final LegacyMaterialMapping itemTypeMapping;

    public CreativeInventoryActionCodec(ProtocolItemTypeMapping itemTypeMapping) {
        this.itemTypeMapping = itemTypeMapping;
    }

    @Override
    public CreativeInventoryActionPacket decode(ByteBuf byteBuf) {
        short slot = byteBuf.readShort();
        ItemStack clickedItem = readItemStack(byteBuf);

        return new CreativeInventoryActionPacket(slot, clickedItem);
    }

    @Nullable
    private ItemStack readItemStack(ByteBuf byteBuf) {
        short itemID = byteBuf.readShort();

        if (itemID != -1) {
            byte itemCount = byteBuf.readByte();
            short itemMetadata = byteBuf.readShort();
            ItemType itemType = itemTypeMapping.getItemType(itemID, itemMetadata);
            Preconditions.checkNotNull(itemType, "Cannot find itemType with ID " + itemID);
            byte nbtStartByte = byteBuf.readByte(); // TODO Item NBT
            return new SimpleItemStack(itemType, itemCount, null);
        }
        return null;
    }
}
