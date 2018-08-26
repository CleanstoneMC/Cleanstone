package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class ByteTag extends AbstractTag<Byte> {

    private Byte value;

    public ByteTag() {
        super();
    }

    public ByteTag(Byte value) {
        super(value);
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        this.value = buffer.get();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("ByteTag has no value.");
        }
        buffer.put(value);
    }

    @Override
    public TagType getType() {
        return VanillaTagType.BYTE;
    }
}
