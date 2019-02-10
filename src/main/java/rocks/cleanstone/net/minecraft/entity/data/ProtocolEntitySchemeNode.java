package rocks.cleanstone.net.minecraft.entity.data;

import javax.annotation.Nullable;

public class ProtocolEntitySchemeNode {

    private final String id, ancestor;
    private final Integer typeID;
    private final ProtocolEntitySchemeMetadataEntry[] metadata;

    public ProtocolEntitySchemeNode(String id, String ancestor, Integer typeID,
                                    ProtocolEntitySchemeMetadataEntry[] metadata) {
        this.id = id;
        this.ancestor = ancestor;
        this.typeID = typeID;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getAncestor() {
        return ancestor;
    }

    @Nullable
    public Integer getTypeID() {
        return typeID;
    }

    public ProtocolEntitySchemeMetadataEntry[] getMetadataEntries() {
        return metadata;
    }
}
