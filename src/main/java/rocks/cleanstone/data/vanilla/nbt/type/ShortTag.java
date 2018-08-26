package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class ShortTag extends AbstractTag<Short> {

    private Short value;

    public ShortTag() {
        super();
    }

    public ShortTag(Short value) {
        super(value);
        this.value = value;
    }

    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.getShort();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("ShortTag has no value.");
        }
        buffer.putShort(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.SHORT;
    }
}
