package rocks.cleanstone.io.vanilla.nbt.type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

public class StringTag extends AbstractTag<String> {

    public StringTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public String get() {
        return Arrays.toString(ByteBuffer.wrap(this.rawData).order(ByteOrder.BIG_ENDIAN).asCharBuffer().array());
    }

    @Override
    public TagType getType() {
        return VanillaTagType.STRING;
    }
}
