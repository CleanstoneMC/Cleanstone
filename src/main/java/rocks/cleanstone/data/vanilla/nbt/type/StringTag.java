package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

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
