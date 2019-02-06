package rocks.cleanstone.net.minecraft.entity.metadata.value;

import java.util.UUID;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OptionalUUIDValue implements EntityMetadataEntryValue {
    @Nullable
    private final UUID uuid;

    private OptionalUUIDValue(@Nullable UUID uuid) {
        this.uuid = uuid;
    }

    public static OptionalUUIDValue of(@Nullable UUID uuid) {
        return new OptionalUUIDValue(uuid);
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
