package rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata;

import com.google.common.base.Preconditions;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntitySchemeMetadataEntry;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntitySchemeNode;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.entrydata.EntityMetadataEntryData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    public void writeMetadata(VanillaEntityMetadataEntryID metadataEntryID, EntityMetadataEntryData metadataEntryData) {
        Preconditions.checkNotNull(metadataEntryID, "metadataEntry cannot be null");
        Preconditions.checkNotNull(metadataEntryData, "value cannot be null");

        ProtocolEntitySchemeMetadataEntry metadataEntryScheme = possibleMetadataEntries.stream()
                .filter(entry -> entry.getId().equals(metadataEntryID.getName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException(
                        "Cannot resolve metadataEntry " + metadataEntryID.getName())
                );
        int index = metadataEntryScheme.getIndex();
        VanillaEntityMetadataEntryDataType metadataEntryType = VanillaEntityMetadataEntryDataType.fromValueClass(
                metadataEntryData.getClass());
        Preconditions.checkNotNull(metadataEntryType, "Cannot resolve metadataEntryType "
                + metadataEntryScheme.getType());

        metadataEntries.add(new EntityMetadataEntry(index, metadataEntryType, metadataEntryData));
    }

    public EntityMetadata build() {
        return new EntityMetadata(metadataEntries);
    }
}
