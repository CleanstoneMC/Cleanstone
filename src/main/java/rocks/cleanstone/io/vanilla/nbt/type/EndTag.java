package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.VanillaTagType;

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
