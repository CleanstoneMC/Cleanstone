package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class DoubleTag extends AbstractTag<Double> {

    private Double value;

    public DoubleTag() {
        super();
    }

    public DoubleTag(Double value) {
        super(value);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.getDouble();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("DoubleTag has no value.");
        }
        buffer.putDouble(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.DOUBLE;
    }
}
