package rocks.cleanstone.net.minecraft.entity.metadata;

import java.util.Arrays;

import javax.annotation.Nullable;

import rocks.cleanstone.net.minecraft.entity.metadata.value.BooleanValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.ByteValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.ChatValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.EntityMetadataEntryValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.FloatValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.OptionalChatValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.OptionalPositionValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.OptionalUUIDValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.PositionValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.StringValue;
import rocks.cleanstone.net.minecraft.entity.metadata.value.VarIntValue;

public enum EntityMetadataEntryType {
    BYTE(0, "byte", ByteValue.class),
    VAR_INT(1, "varInt", VarIntValue.class),
    FLOAT(2, "float", FloatValue.class),
    STRING(3, "string", StringValue.class),
    CHAT(4, "chat", ChatValue.class),
    OPTIONAL_CHAT(5, "optChat", OptionalChatValue.class),
    // SLOT(6, "slot", SlotType.class), TODO
    BOOLEAN(7, "boolean", BooleanValue.class),
    // ROTATION(8, "rotation", RotationType.class), TODO
    POSITION(9, "position", PositionValue.class),
    OPTIONAL_POSITION(10, "optPosition", OptionalPositionValue.class),
    // DIRECTION(11, "direction", DirectionType.class), TODO
    OPTIONAL_UUID(12, "optUUID", OptionalUUIDValue.class);
    // OPTIONAL_BLOCK_ID(13, "optBlockID", OptionalBlockIDValue.class), TODO
    // NBT(14, "nbt", NBTValue.class), TODO
    // PARTICLE(15, "particle", ParticleValue.class); TODO

    private final int typeID;
    private final String name;
    private final Class<? extends EntityMetadataEntryValue> valueClass;

    EntityMetadataEntryType(int typeID, String name, Class<? extends EntityMetadataEntryValue> valueClass) {
        this.typeID = typeID;
        this.name = name;
        this.valueClass = valueClass;
    }

    @Nullable
    public static EntityMetadataEntryType fromValueClass(Class<? extends EntityMetadataEntryValue> entryValueClass) {
        return Arrays.stream(values()).filter(type -> type.getValueClass().isAssignableFrom(entryValueClass))
                .findAny().orElse(null);
    }

    public int getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }

    public Class<? extends EntityMetadataEntryValue> getValueClass() {
        return valueClass;
    }
}
