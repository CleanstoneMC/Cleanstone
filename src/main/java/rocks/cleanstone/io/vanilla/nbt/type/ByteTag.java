package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Coded by fionera.
 */
public class ByteTag extends AbstractTag<Byte> {

    public ByteTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Byte get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).get();
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.BYTE;
    }
}
