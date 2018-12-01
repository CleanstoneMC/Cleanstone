package rocks.cleanstone.net.minecraft.protocol.v1_13_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.inventory.item.SimpleItemStack;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.game.material.item.mapping.ItemTypeMapping;
import rocks.cleanstone.net.minecraft.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import javax.annotation.Nullable;
import java.io.IOException;

@Slf4j
@Component
public class CreativeInventoryActionCodec implements InboundPacketCodec<CreativeInventoryActionPacket> {

    private final ItemTypeMapping<Integer> itemTypeMapping;

    public CreativeInventoryActionCodec(@Qualifier("protocolItemTypeMapping_v1_13_2") ItemTypeMapping<Integer> itemTypeMapping) {
        this.itemTypeMapping = itemTypeMapping;
    }

    @Override
    public CreativeInventoryActionPacket decode(ByteBuf byteBuf) throws IOException {
        short slot = byteBuf.readShort();
        ItemStack clickedItem = readItemStack(byteBuf);

        return new CreativeInventoryActionPacket(slot, clickedItem);
    }

    @Nullable
    private ItemStack readItemStack(ByteBuf byteBuf) throws IOException {
        boolean present = byteBuf.readBoolean();

        if (present) {
            int itemID = ByteBufUtils.readVarInt(byteBuf);
            log.info("Reading itemID " + itemID + " with " + itemTypeMapping.getClass().getSimpleName());

            byte itemCount = byteBuf.readByte();
            ItemType itemType = itemTypeMapping.getItemType(itemID);
            Preconditions.checkNotNull(itemType, "Cannot find itemType with ID " + itemID);
            byte nbtStartByte = byteBuf.readByte(); // TODO Item NBT
            return new SimpleItemStack(itemType, itemCount, null);
        }
        return null;
    }
}
