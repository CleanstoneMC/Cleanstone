package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class IntTag extends AbstractTag<Integer> {

    private Integer value;

    public IntTag() {
        super();
    }

    public IntTag(Integer value) {
        super(value);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.getInt();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("IntTag has no value.");
        }
        buffer.putInt(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.INT;
    }
}
