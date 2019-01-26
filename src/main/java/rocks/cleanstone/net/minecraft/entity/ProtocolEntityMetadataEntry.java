package rocks.cleanstone.net.minecraft.entity;

public class ProtocolEntityMetadataEntry {
    private final String id;
    private final int index;
    private final String type;

    public ProtocolEntityMetadataEntry(String id, int index, String type) {
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
}
