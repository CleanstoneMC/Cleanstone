package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntArrayTag extends AbstractTag<int[]> {

    private int[] value;

    public IntArrayTag() {
        super();
    }

    public IntArrayTag(int[] value) {
        super(value);
        this.value = value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        int length = buffer.getInt();
        value = new int[length];
        IntBuffer intBuffer = buffer.asIntBuffer();
        intBuffer.get(value);
        // sync position
        buffer.position(buffer.position() + 4 * length);
        buffer.mark();
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("IntArrayTag has no value.");
        }
        int length = value.length;
        buffer.putInt(length);
        IntBuffer intBuffer = buffer.asIntBuffer();
        intBuffer.put(value);
        // sync position
        buffer.position(buffer.position() + 4 * length);
        buffer.mark();
    }

    @Override
    public int[] getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return VanillaTagType.INT_ARRAY;
    }
}
