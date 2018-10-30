package rocks.cleanstone.game.entity.metadata.type;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.metadata.type.types.VarInt;
import rocks.cleanstone.net.minecraft.packet.enums.Direction;
import rocks.cleanstone.net.minecraft.packet.enums.Particle;

import java.util.Optional;

public enum VanillaMetadataType implements MetadataType {
    BYTE(0, Byte.class),
    VAR_INT(1, VarInt.class),
    FLOAT(2, Float.class),
    STRING(3, String.class),
    CHAT(4, ChatMessage.class),
    OPT_CHAT(5, Optional.class),
    SLOT(6, Integer.class),
    BOOLEAN(7, Boolean.class),
    ROTATION(8, Rotation.class),
    POSITION(9, Position.class),
    OPT_POSITION(10, Optional.class),
    DIRECTION(11, Direction.class),
    OPT_UUID(12, Optional.class),
    OPT_BLOCKID(13, Optional.class),
    NBT_TAG(14, NamedBinaryTag.class),
    PARTICLE(15, Particle.class);

    private final int typeID;
    private final Class typeClass;

    VanillaMetadataType(int typeID, Class typeClass) {
        this.typeID = typeID;
        this.typeClass = typeClass;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }

    @Override
    public Class getTypeClass() {
        return typeClass;
    }
}
