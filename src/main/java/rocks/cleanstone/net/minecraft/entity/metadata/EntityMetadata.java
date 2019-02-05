package rocks.cleanstone.net.minecraft.entity.metadata;

import java.util.Collection;

import rocks.cleanstone.net.minecraft.entity.VanillaEntity;

/**
 * <p>Metadata for a minecraft {@link VanillaEntity} as required by a minecraft client</p>
 * Created using an {@link EntityMetadataBuilder}
 */
public class EntityMetadata implements AutoCloseable {

    private final Collection<EntityMetadataEntry> metadataEntries;

    public EntityMetadata(Collection<EntityMetadataEntry> metadataEntries) {
        this.metadataEntries = metadataEntries;
    }

    public Collection<EntityMetadataEntry> getMetadataEntries() {
        return metadataEntries;
    }

    @Override
    public void close() {
        metadataEntries.forEach(entry -> entry.getValue().release());
    }
}
