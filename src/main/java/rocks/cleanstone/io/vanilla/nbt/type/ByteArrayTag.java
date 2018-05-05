package rocks.cleanstone.io.vanilla.nbt.type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

public class ByteArrayTag extends AbstractTag<byte[]> {

    public ByteArrayTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public byte[] get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).array();
    }

    @Override
    public TagType getType() {
        return VanillaTagType.BYTE_ARRAY;
    }
}
