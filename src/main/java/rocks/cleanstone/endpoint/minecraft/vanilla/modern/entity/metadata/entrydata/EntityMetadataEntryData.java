package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.EntityMetadataEntry;

/**
 * Serializable data for a {@link EntityMetadataEntry} as recognized by the minecraft client
 */
public interface EntityMetadataEntryData {
    ByteBuf serialize();
}
