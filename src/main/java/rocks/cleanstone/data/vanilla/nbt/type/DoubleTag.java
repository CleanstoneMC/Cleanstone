package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DoubleTag extends AbstractTag<Double> {

    public DoubleTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Double get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asDoubleBuffer().get();
    }

    @Override
    public TagType getType() {
        return VanillaTagType.DOUBLE;
    }
}
