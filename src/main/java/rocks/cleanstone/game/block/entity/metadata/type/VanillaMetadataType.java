package rocks.cleanstone.game.block.entity.metadata.type;

public enum VanillaMetadataType implements MetadataType {
    BYTE(0),
    VAR_INT(1),
    FLOAT(2),
    STRING(3),
    CHAT(4),
    SLOT(5),
    BOOLEAN(6),
    ROTATION(7),
    POSITION(8),
    OPT_POSITION(9),
    DIRECTION(10),
    OPT_UUID(11),
    OPT_BLOCKID(12),
    NBT_TAG(13);

    private final int typeID;

    VanillaMetadataType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
