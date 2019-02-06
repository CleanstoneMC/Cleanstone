package rocks.cleanstone.net.minecraft.entity.metadata.value;

import io.netty.buffer.ByteBuf;

public interface EntityMetadataEntryValue {
    ByteBuf serialize();
}
