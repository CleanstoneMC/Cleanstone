package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

public class IntArrayTag<T extends AbstractTag> extends AbstractTag<int[]> {

    public IntArrayTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public int[] get() {
        return null; //TODO
    }

    @Override
    public TagType getType() {
        return VanillaTagType.INT_ARRAY;
    }
}
