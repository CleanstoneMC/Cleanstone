package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Coded by fionera.
 */
public class ShortTag extends AbstractTag<Short> {

    public ShortTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Short get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get();
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.SHORT;
    }
}
