package rocks.cleanstone.storage.world;

public enum StandardWorldDataType implements WorldDataType {
    CHUNK_DATA(1), BLOCK_TYPE_IDS(2), PROPERTY_IDS(3);

    private final int typeID;

    StandardWorldDataType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
