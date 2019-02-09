package rocks.cleanstone.net.minecraft.entity.metadata;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import rocks.cleanstone.net.minecraft.entity.VanillaEntity;

/**
 * <p>Metadata for a minecraft {@link VanillaEntity} as required by a minecraft client</p>
 * Created using an {@link EntityMetadataBuilder}
 */
public class EntityMetadata {

    private final Collection<EntityMetadataEntry> metadataEntries;

    public EntityMetadata(Collection<EntityMetadataEntry> metadataEntries) {
        this.metadataEntries = metadataEntries;
    }

    public EntityMetadata(EntityMetadata... metadata) {
        this.metadataEntries = new HashSet<>();
        Arrays.stream(metadata).map(EntityMetadata::getMetadataEntries)
                .forEach(metadataEntries::addAll);
    }

    public Collection<EntityMetadataEntry> getMetadataEntries() {
        return metadataEntries;
    }
}
