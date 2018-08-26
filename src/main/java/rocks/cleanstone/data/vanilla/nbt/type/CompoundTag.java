package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class CompoundTag extends AbstractTag<Map<String, AbstractTag>> {

    private Map<String, AbstractTag> value;

    public CompoundTag() {
        super();
    }

    public CompoundTag(Map<String, AbstractTag> value) {
        super(value);
        this.value = value;
    }

    @Override
    public Map<String, AbstractTag> getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        Map<String, AbstractTag> value = new HashMap<>();

        // todo: read compound

        this.value = value;
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("CompoundTag has no value.");
        }
        // todo: write compound
    }

    @Override
    public TagType getType() {
        return VanillaTagType.COMPOUND;
    }
}
