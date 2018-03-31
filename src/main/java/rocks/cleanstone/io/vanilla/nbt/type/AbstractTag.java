package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagTypeInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Coded by fionera.
 */
public abstract class AbstractTag<T> {

    protected byte[] rawData;

    public AbstractTag(byte[] rawData) {
        this.rawData = rawData;
    }

    public abstract T get();

    public abstract TagTypeInterface getType();

}
