package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LongTag extends AbstractTag<Long> {

    public LongTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Long get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asLongBuffer().get();
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LONG;
    }
}
