package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;
import rocks.cleanstone.io.vanilla.nbt.TagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class IntTag extends AbstractTag<Integer> {

    public IntTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Integer get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asIntBuffer().get();
    }

    @Override
    public TagType getType() {
        return VanillaTagType.INT;
    }
}
