package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.VanillaEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
