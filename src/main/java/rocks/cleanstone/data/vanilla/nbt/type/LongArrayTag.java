package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class LongArrayTag extends AbstractTag<long[]> {

    private long[] value;

    public LongArrayTag() {
        super();
    }

    public LongArrayTag(long[] value) {
        super(value);
        this.value = value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        int length = buffer.getInt();
        value = new long[length];
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.get(value);
        // sync position
        buffer.position(buffer.position() + 8 * length);
        buffer.mark();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("LongArrayTag has no value.");
        }
        int length = value.length;
        buffer.putInt(length);
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.put(value);
        // sync position
        buffer.position(buffer.position() + 8 * length);
        buffer.mark();
    }

    @Override
    public long[] getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LONG_ARRAY;
    }
}
