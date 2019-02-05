package rocks.cleanstone.net.minecraft.entity.data;

import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadataType;
import rocks.cleanstone.net.minecraft.entity.metadata.types.EntityMetadataTypeInterface;

public class ProtocolEntityMetadataEntry {
    private final String id;
    private final int index;
    private final EntityMetadataType type;

    public ProtocolEntityMetadataEntry(String id, int index, EntityMetadataType type, EntityMetadataTypeInterface defaultValue) {
        this.id = id;
        this.index = index;
        this.type = type;
    }

    public static ProtocolEntityMetadataEntry entryOf(String id, int index, EntityMetadataType type, EntityMetadataTypeInterface defaultValue) {
        return new ProtocolEntityMetadataEntry(id, index, type, defaultValue);
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public EntityMetadataType getType() {
        return type;
    }
}
