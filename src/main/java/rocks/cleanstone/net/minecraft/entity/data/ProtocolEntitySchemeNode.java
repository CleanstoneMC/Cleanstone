package rocks.cleanstone.net.minecraft.entity.data;

import javax.annotation.Nullable;

public class ProtocolEntitySchemeNode {

    private final String id, ancestor;
    private final ProtocolEntitySchemeMetadataEntry[] metadata;

    public ProtocolEntitySchemeNode(String id, String ancestor, ProtocolEntitySchemeMetadataEntry[] metadata) {
        this.id = id;
        this.ancestor = ancestor;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getAncestor() {
        return ancestor;
    }

    public ProtocolEntitySchemeMetadataEntry[] getMetadataEntries() {
        return metadata;
    }
}
