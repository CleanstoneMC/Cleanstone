package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class AbstractTag<T> {

    public AbstractTag() {
    }

    public AbstractTag(T value) {
    }

    public abstract T getValue();

    public abstract AbstractTag loadBuffer(ByteBuffer buffer) throws IOException;

    public abstract void writeToBuffer(ByteBuffer buffer) throws IOException;

    public abstract TagType getType();

}
