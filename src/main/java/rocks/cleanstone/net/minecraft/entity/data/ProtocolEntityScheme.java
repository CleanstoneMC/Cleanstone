package rocks.cleanstone.net.minecraft.entity.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import rocks.cleanstone.net.minecraft.entity.VanillaEntity;

/**
 * A minecraft client dependent entity scheme containing the information that's required to create {@link
 * VanillaEntity}s
 */
public class ProtocolEntityScheme {

    private final ProtocolEntitySchemeNode[] nodes;

    public ProtocolEntityScheme(ProtocolEntitySchemeNode[] nodes) {
        this.nodes = nodes;
    }

    public ProtocolEntitySchemeNode[] getNodes() {
        return nodes;
    }

    @Nullable
    public ProtocolEntitySchemeNode getEntityNodeByID(String id) {
        return Arrays.stream(nodes).filter(node -> node.getId().equals(id)).findAny().orElse(null);
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
