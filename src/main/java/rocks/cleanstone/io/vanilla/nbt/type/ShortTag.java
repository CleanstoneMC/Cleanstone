package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;
import rocks.cleanstone.io.vanilla.nbt.TagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ShortTag extends AbstractTag<Short> {

    public ShortTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Short get() {
        return ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get();
    }

    @Override
    public TagType getType() {
        return VanillaTagType.SHORT;
    }
}
