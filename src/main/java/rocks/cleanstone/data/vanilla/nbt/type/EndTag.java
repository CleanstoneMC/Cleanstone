package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;

public class EndTag extends AbstractTag<Void> {

    public EndTag() {
        super();
    }

    private EndTag(Void value) {
    }

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
    }

    @Override
    public TagType getType() {
        return VanillaTagType.END;
    }
}
