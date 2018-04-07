package rocks.cleanstone.io.vanilla.nbt.type;

import rocks.cleanstone.io.vanilla.nbt.TagType;

public abstract class AbstractTag<T> {

    protected byte[] rawData;

    public AbstractTag(byte[] rawData) {
        this.rawData = rawData;
    }

    public abstract T get();

    public abstract TagType getType();

}
