package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

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
