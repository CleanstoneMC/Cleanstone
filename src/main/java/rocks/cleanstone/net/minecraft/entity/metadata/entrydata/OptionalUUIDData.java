package rocks.cleanstone.net.minecraft.entity.metadata.entrydata;

import java.util.UUID;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OptionalUUIDData implements EntityMetadataEntryData {
    @Nullable
    private final UUID uuid;

    private OptionalUUIDData(@Nullable UUID uuid) {
        this.uuid = uuid;
    }

    public static OptionalUUIDData of(@Nullable UUID uuid) {
        return new OptionalUUIDData(uuid);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(uuid != null);
        if (uuid != null) {
            ByteBufUtils.writeUUID(byteBuf, uuid);
        }
        return byteBuf;
    }
}
