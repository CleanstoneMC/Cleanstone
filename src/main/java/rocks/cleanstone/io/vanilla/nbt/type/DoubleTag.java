package rocks.cleanstone.io.vanilla.nbt.type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

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
