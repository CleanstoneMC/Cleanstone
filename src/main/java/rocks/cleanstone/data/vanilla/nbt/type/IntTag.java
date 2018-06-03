package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

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
