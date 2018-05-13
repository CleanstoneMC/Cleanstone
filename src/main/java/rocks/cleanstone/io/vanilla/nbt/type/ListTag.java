package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

import java.util.List;

public class ListTag<T extends AbstractTag> extends AbstractTag<List<T>> {

    public ListTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public List<T> get() {

        return null; //TODO
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LIST;
    }
}
