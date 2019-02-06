package rocks.cleanstone.net.minecraft.entity.metadata;

import java.util.Arrays;

import javax.annotation.Nullable;

import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.BooleanData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.ByteData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.ChatData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.EntityMetadataEntryData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.FloatData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.OptionalChatData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.OptionalPositionData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.OptionalUUIDData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.PositionData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.StringData;
import rocks.cleanstone.net.minecraft.entity.metadata.entrydata.VarIntData;

/**
 * A collection of all {@link EntityMetadataEntryData} types
 */
public enum VanillaEntityMetadataEntryDataType {
    BYTE(0, "byte", ByteData.class),
    VAR_INT(1, "varInt", VarIntData.class),
    FLOAT(2, "float", FloatData.class),
    STRING(3, "string", StringData.class),
    CHAT(4, "chat", ChatData.class),
    OPTIONAL_CHAT(5, "optChat", OptionalChatData.class),
    // SLOT(6, "slot", SlotData.class), TODO
    BOOLEAN(7, "boolean", BooleanData.class),
    // ROTATION(8, "rotation", RotationData.class), TODO
    POSITION(9, "position", PositionData.class),
    OPTIONAL_POSITION(10, "optPosition", OptionalPositionData.class),
    // DIRECTION(11, "direction", DirectionData.class), TODO
    OPTIONAL_UUID(12, "optUUID", OptionalUUIDData.class);
    // OPTIONAL_BLOCK_ID(13, "optBlockID", OptionalBlockIDData.class), TODO
    // NBT(14, "nbt", NBTData.class), TODO
    // PARTICLE(15, "particle", ParticleData.class); TODO

    private final int typeID;
    private final String name;
    private final Class<? extends EntityMetadataEntryData> dataClass;

    VanillaEntityMetadataEntryDataType(int typeID, String name, Class<? extends EntityMetadataEntryData> dataClass) {
        this.typeID = typeID;
        this.name = name;
        this.dataClass = dataClass;
    }

    @Nullable
    public static VanillaEntityMetadataEntryDataType fromValueClass(Class<? extends EntityMetadataEntryData> entryDataClass) {
        return Arrays.stream(values()).filter(type -> type.getDataClass().isAssignableFrom(entryDataClass))
                .findAny().orElse(null);
    }

    public int getTypeID() {
        return typeID;
    }

    public String getName() {
        return name;
    }

    public Class<? extends EntityMetadataEntryData> getDataClass() {
        return dataClass;
    }
}
