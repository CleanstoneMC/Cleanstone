package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.util.Map;

public class CompoundTag extends AbstractTag<Map<String, AbstractTag>> {

    public CompoundTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Map<String, AbstractTag> get() {
        // TODO
        return null;
    }

    @Override
    public TagType getType() {
        return VanillaTagType.COMPOUND;
    }
}
