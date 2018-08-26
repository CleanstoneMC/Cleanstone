package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class ByteArrayTag extends AbstractTag<byte[]> {

    private byte[] value;

    public ByteArrayTag() {
        super();
    }

    public ByteArrayTag(byte[] value) {
        super(value);
        this.value = value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        int length = buffer.getInt();
        value = new byte[length];
        buffer.get(value);
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("ByteArrayTag has no value.");
        }
        int length = value.length;
        buffer.putInt(length);
        buffer.put(value);
    }

    @Override
    public byte[] getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return VanillaTagType.BYTE_ARRAY;
    }
}
