package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata.entrydata.EntityMetadataEntryData;

/**
 * An entry for the {@link EntityMetadata} which is created using a {@link EntityMetadataBuilder}
 */
public class EntityMetadataEntry {

    private final int index;
    private final VanillaEntityMetadataEntryDataType type;
    private final EntityMetadataEntryData data;

    public EntityMetadataEntry(int index, VanillaEntityMetadataEntryDataType type, EntityMetadataEntryData data) {
        this.index = index;
        this.type = type;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public VanillaEntityMetadataEntryDataType getType() {
        return type;
    }

    public EntityMetadataEntryData getData() {
        return data;
    }
}
