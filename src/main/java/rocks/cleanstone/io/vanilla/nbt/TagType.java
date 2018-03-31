package rocks.cleanstone.io.vanilla.nbt;

/**
 * Coded by fionera.
 */
public enum TagType implements TagTypeInterface {
    END(0),
    BYTE(1),
    SHORT(2),
    INT(3),
    LONG(4),
    FLOAT(5),
    DOUBLE(6),
    BYTE_ARRAY(7),
    STRING(8),
    LIST(9),
    COMPOUND(10);

    private final int typeId;

    TagType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
