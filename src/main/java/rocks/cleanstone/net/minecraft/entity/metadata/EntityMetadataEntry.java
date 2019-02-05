package rocks.cleanstone.net.minecraft.entity.metadata;

import io.netty.buffer.ByteBuf;

/**
 * An entry for the {@link EntityMetadata} which is created using a {@link EntityMetadataBuilder}
 */
public class EntityMetadataEntry {

    private final int index;
    private final EntityMetadataType type;
    private final ByteBuf value;

    public EntityMetadataEntry(int index, EntityMetadataType type, ByteBuf value) {
        this.index = index;
        this.type = type;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public EntityMetadataType getType() {
        return type;
    }

    public ByteBuf getValue() {
        return value;
    }
}
