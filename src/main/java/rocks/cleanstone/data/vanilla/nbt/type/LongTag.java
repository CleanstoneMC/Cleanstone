package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class LongTag extends AbstractTag<Long> {

    private Long value;

    public LongTag() {
        super();
    }

    public LongTag(Long value) {
        super(value);
        this.value = value;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.getLong();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("LongTag has no value.");
        }
        buffer.putLong(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LONG;
    }
}
