package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Coded by fionera.
 */
public class StringTag extends AbstractTag<String> {

    public StringTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public String get() {
        return Arrays.toString(ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asCharBuffer().array());
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.STRING;
    }
}
