package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Coded by fionera.
 */
public class FloatTag extends AbstractTag<Float> {

    public FloatTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Float get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asFloatBuffer().get();
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.FLOAT;
    }
}
