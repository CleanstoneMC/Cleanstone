package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.data;

import java.util.Objects;

public class ProtocolEntitySchemeMetadataEntry {

    private final String id;
    private final int index;
    private final String type;

    public ProtocolEntitySchemeMetadataEntry(String id, int index, String type) {
        this.id = id;
        this.index = index;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProtocolEntitySchemeMetadataEntry)) return false;
        ProtocolEntitySchemeMetadataEntry that = (ProtocolEntitySchemeMetadataEntry) o;
        return index == that.index &&
                Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, index, type);
    }
}
