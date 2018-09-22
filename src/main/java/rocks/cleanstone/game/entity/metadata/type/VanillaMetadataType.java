package rocks.cleanstone.game.entity.metadata.type;

public enum VanillaMetadataType implements MetadataType {
    BYTE(0),
    VAR_INT(1),
    FLOAT(2),
    STRING(3),
    CHAT(4),
    OPT_CHAT(5),
    SLOT(6),
    BOOLEAN(7),
    ROTATION(8),
    POSITION(9),
    OPT_POSITION(10),
    DIRECTION(11),
    OPT_UUID(12),
    OPT_BLOCKID(13),
    NBT_TAG(14),
    PARTICLE(15);

    private final int typeID;

    VanillaMetadataType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
