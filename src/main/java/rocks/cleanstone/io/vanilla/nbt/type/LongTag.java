package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Coded by fionera.
 */
public class LongTag extends AbstractTag<Long> {

    public LongTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Long get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asLongBuffer().get();
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.LONG;
    }
}
