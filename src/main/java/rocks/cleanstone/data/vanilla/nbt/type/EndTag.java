package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

public class EndTag extends AbstractTag<Void> {

    public EndTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public Void get() {
        return null;
    }

    @Override
    public TagType getType() {
        return VanillaTagType.END;
    }
}
