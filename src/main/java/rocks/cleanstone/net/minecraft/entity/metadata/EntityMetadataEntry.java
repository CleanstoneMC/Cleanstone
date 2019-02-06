package rocks.cleanstone.net.minecraft.entity.metadata;

import rocks.cleanstone.net.minecraft.entity.metadata.value.EntityMetadataEntryValue;

/**
 * An entry for the {@link EntityMetadata} which is created using a {@link EntityMetadataBuilder}
 */
public class EntityMetadataEntry {

    private final int index;
    private final EntityMetadataEntryType type;
    private final EntityMetadataEntryValue value;

    public EntityMetadataEntry(int index, EntityMetadataEntryType type, EntityMetadataEntryValue value) {
        this.index = index;
        this.type = type;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public EntityMetadataEntryType getType() {
        return type;
    }

    public EntityMetadataEntryValue getValue() {
        return value;
    }
}
