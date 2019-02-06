package rocks.cleanstone.net.minecraft.entity.metadata;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntitySchemeMetadataEntry;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntitySchemeNode;
import rocks.cleanstone.net.minecraft.entity.metadata.value.EntityMetadataEntryValue;

/**
 * Utility class for creating {@link EntityMetadata} based on a {@link ProtocolEntityScheme}
 */
public class EntityMetadataBuilder {

    private final Collection<ProtocolEntitySchemeMetadataEntry> possibleMetadataEntries;
    private final Set<EntityMetadataEntry> metadataEntries = new HashSet<>();

    public EntityMetadataBuilder(ProtocolEntityScheme protocolEntityScheme, String sourceEntityID) {
        ProtocolEntitySchemeNode entityNode = protocolEntityScheme.getEntityNodeByID(sourceEntityID);
        Preconditions.checkNotNull(entityNode, "Cannot resolve entity node " + sourceEntityID);
        possibleMetadataEntries = protocolEntityScheme.getMetadataEntriesOfNodeRecursively(entityNode);
    }

    public void writeMetadata(String metadataEntryID, EntityMetadataEntryValue metadataEntryValue) {
        Preconditions.checkNotNull(metadataEntryID, "metadataEntryID cannot be null");
        Preconditions.checkNotNull(metadataEntryValue, "value cannot be null");

        ProtocolEntitySchemeMetadataEntry metadataEntryScheme = possibleMetadataEntries.stream()
                .filter(entry -> entry.getId().equals(metadataEntryID))
                .findAny().orElseThrow(() -> new IllegalArgumentException(
                        "Cannot resolve metadataEntryID " + metadataEntryID)
                );
        int index = metadataEntryScheme.getIndex();
        EntityMetadataEntryType metadataEntryType = EntityMetadataEntryType.fromValueClass(
                metadataEntryValue.getClass());
        Preconditions.checkNotNull(metadataEntryType, "Cannot resolve metadataType "
                + metadataEntryScheme.getType());

        metadataEntries.add(new EntityMetadataEntry(index, metadataEntryType, metadataEntryValue));
    }

    public EntityMetadata build() {
        return new EntityMetadata(metadataEntries);
    }
}
