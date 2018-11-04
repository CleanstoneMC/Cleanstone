package rocks.cleanstone.game.entity.metadata.type;

import rocks.cleanstone.game.entity.metadata.codecs.ByteCodec;
import rocks.cleanstone.game.entity.metadata.codecs.VarIntCodec;

public enum VanillaMetadataType implements MetadataType {
    BYTE(0, ByteCodec.class),
    VAR_INT(1, VarIntCodec.class),
    FLOAT(2, null),
    STRING(3, null),
    CHAT(4, null),
    OPT_CHAT(5, null),
    SLOT(6, null),
    BOOLEAN(7, null),
    ROTATION(8, null),
    POSITION(9, null),
    OPT_POSITION(10, null),
    DIRECTION(11, null),
    OPT_UUID(12, null),
    OPT_BLOCKID(13, null),
    NBT_TAG(14, null),
    PARTICLE(15, null);

    private final int typeID;
    private final Class codecClass;

    VanillaMetadataType(int typeID, Class codecClass) {
        this.typeID = typeID;
        this.codecClass = codecClass;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }

    @Override
    public Class getCodecClass() {
        return codecClass;
    }
}
