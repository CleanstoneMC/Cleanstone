package rocks.cleanstone.storage.chunk;

public enum StandardChunkDataType implements ChunkDataType {
    BLOCKS(1), ENTITIES(2), BLOCK_ENTITIES(3), BIOME_STATE(4);

    private final int typeID;

    StandardChunkDataType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
