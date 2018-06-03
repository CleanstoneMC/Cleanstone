package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
