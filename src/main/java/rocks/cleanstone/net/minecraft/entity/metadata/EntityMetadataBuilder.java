package rocks.cleanstone.net.minecraft.entity.metadata;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntitySchemeMetadataEntry;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntitySchemeNode;

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

    public void writeMetadata(String metadataEntryID, ByteBuf value) {
        Preconditions.checkNotNull(metadataEntryID, "metadataEntryID cannot be null");
        Preconditions.checkNotNull(value, "value cannot be null");

        ProtocolEntitySchemeMetadataEntry metadataEntryScheme = possibleMetadataEntries.stream()
                .filter(entry -> entry.getId().equals(metadataEntryID))
                .findAny().orElseThrow(
                        () -> new IllegalArgumentException("Cannot resolve metadataEntryID " + metadataEntryID)
                );
        int index = metadataEntryScheme.getIndex();
        EntityMetadataType metadataType = EntityMetadataType.byName(metadataEntryScheme.getType());
        Preconditions.checkNotNull(metadataType, "Cannot resolve metadataType " + metadataEntryScheme.getType());

        metadataEntries.add(new EntityMetadataEntry(index, metadataType, value));
    }

    public EntityMetadata build() {
        return new EntityMetadata(metadataEntries);
    }
}
