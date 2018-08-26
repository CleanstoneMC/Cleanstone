package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.nio.ByteBuffer;
import java.util.List;

public class ListTag<T extends AbstractTag> extends AbstractTag<List<T>> {

    private List<T> value;

    public ListTag() {
    }

    public ListTag(List<T> value) {
        super(value);
        this.value = value;
    }

    @Override
    public List<T> getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) {
        // todo
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("ListTag has no value.");
        }
        // todo
    }

    @Override
    public TagType getType() {
        return VanillaTagType.LIST;
    }
}
