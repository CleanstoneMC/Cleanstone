package rocks.cleanstone.net.minecraft.entity.metadata;

import java.util.Arrays;

import javax.annotation.Nullable;

public enum EntityMetadataType {
    BYTE(0, "byte"),
    VAR_INT(1, "varInt"),
    FLOAT(2, "float"),
    STRING(3, "string"),
    CHAT(4, "chat"),
    OPTIONAL_CHAT(5, "optChat"),
    SLOT(6, "slot"),
    BOOLEAN(7, "boolean"),
    ROTATION(8, "rotation"),
    POSITION(9, "position"),
    OPTIONAL_POSITION(10, "optPosition"),
    DIRECTION(11, "direction"),
    OPTIONAL_UUID(12, "optUUID"),
    OPTIONAL_BLOCK_ID(13, "optBlockID"),
    NBT(14, "nbt"),
    PARTICLE(15, "particle");

    private final int typeID;
    private final String name;

    EntityMetadataType(int typeID, String name) {
        this.typeID = typeID;
        this.name = name;
    }

    @Nullable
    public static EntityMetadataType byName(String name) {
        return Arrays.stream(values()).filter(type -> type.getName().equals(name)).findAny().orElse(null);
    }

    public int getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }
}
