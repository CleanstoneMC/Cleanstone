package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;
import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

/**
 * Coded by fionera.
 */
public class ListTag<T extends AbstractTag> extends AbstractTag<List<T>> {

    public ListTag(byte[] rawData) {
        super(rawData);
    }

    @Override
    public List<T> get() {
        return null; //TODO:
    }

    @Override
    public TagTypeInterface getType() {
        return TagType.LIST;
    }
}
