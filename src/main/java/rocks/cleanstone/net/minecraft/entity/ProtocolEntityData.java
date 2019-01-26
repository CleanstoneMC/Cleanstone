package rocks.cleanstone.net.minecraft.entity;

import javax.annotation.Nullable;

public class ProtocolEntityData {

    private final String id, ancestor;
    private final ProtocolEntityMetadataEntry[] metadata;

    public ProtocolEntityData(String id, String ancestor, ProtocolEntityMetadataEntry[] metadata) {
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

    public ProtocolEntityMetadataEntry[] getMetadataEntries() {
        return metadata;
    }
}
