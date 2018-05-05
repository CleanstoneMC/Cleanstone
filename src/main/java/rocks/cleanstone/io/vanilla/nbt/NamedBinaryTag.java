package rocks.cleanstone.io.vanilla.nbt;

import java.io.File;

import rocks.cleanstone.io.vanilla.nbt.type.AbstractTag;

public class NamedBinaryTag {

    private final File file;
    private final AbstractTag rootTag;
    private String rootTagId;
    private byte rootTagType;

    public NamedBinaryTag(String fileName) {
        this(new File(fileName));
    }

    public NamedBinaryTag(File file) {
        this.file = file;
        // TODO decompress from GZip if needed and read root tag
        rootTag = null;
    }
}
