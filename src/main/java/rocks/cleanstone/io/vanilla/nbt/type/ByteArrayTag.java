package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Coded by fionera.
 */
public class ByteArrayTag extends AbstractTag<byte[]> {

    public ByteArrayTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public byte[] get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).array();
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.BYTE_ARRAY;
    }
}
