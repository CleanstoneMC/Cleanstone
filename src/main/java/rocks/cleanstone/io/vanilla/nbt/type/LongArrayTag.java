package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

public class LongArrayTag<T extends AbstractTag> extends AbstractTag<long[]> {

    public LongArrayTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public long[] get() {
        return null; //TODO
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LONG_ARRAY;
    }
}
