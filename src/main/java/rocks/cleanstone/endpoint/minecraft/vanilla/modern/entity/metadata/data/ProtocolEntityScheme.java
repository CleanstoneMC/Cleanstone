package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.data;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.VanillaEntity;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * A minecraft client dependent entity scheme containing the information that's required to create {@link
 * VanillaEntity}s
 */
public class ProtocolEntityScheme {

    private final ProtocolEntitySchemeNode[] entities;

    public ProtocolEntityScheme(ProtocolEntitySchemeNode[] entities) {
        this.entities = entities;
    }

    public ProtocolEntitySchemeNode[] getNodes() {
        return entities;
    }

    @Nullable
    public ProtocolEntitySchemeNode getEntityNodeByID(String id) {
        return Arrays.stream(entities).filter(node -> node.getId().equals(id)).findAny().orElse(null);
    }

    public Collection<ProtocolEntitySchemeMetadataEntry> getMetadataEntriesOfNodeRecursively(ProtocolEntitySchemeNode node) {
        Set<ProtocolEntitySchemeMetadataEntry> metadataEntries = new HashSet<>();
        while (true) {
            ProtocolEntitySchemeNode finalNode = node;
            Arrays.stream(node.getMetadataEntries()).forEach(entry -> {
                if (!metadataEntries.add(entry)) {
                    throw new NullPointerException("Detected duplicate metadata entry while " +
                            "recursively resolving entity scheme node " + finalNode.getId());
                }
            });
            if (node.getAncestor() == null) {
                return metadataEntries;
            }
            ProtocolEntitySchemeNode nextNode = getEntityNodeByID(node.getAncestor());
            if (nextNode == null) {
                throw new NullPointerException("Cannot resolve ancestor " + node.getAncestor()
                        + " of entity scheme node " + node.getId());
            }
            node = nextNode;
        }
    }
}
