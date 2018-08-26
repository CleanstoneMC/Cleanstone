package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class FloatTag extends AbstractTag<Float> {

    private Float value;

    public FloatTag() {
        super();
    }

    public FloatTag(Float value) {
        super(value);
        this.value = value;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.getFloat();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("FloatTag has no value.");
        }
        buffer.putFloat(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.FLOAT;
    }
}
