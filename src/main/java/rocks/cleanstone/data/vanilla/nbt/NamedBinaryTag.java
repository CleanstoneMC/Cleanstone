package rocks.cleanstone.data.vanilla.nbt;

import rocks.cleanstone.data.vanilla.nbt.type.AbstractTag;

import java.io.File;

public class NamedBinaryTag {

    private String rootTagId;
    private byte rootTagType;

    public NamedBinaryTag(String fileName) {
        this(new File(fileName));
    }

    public NamedBinaryTag(File file) {
        File file1 = file;
        // TODO decompress from GZip if needed and read root tag
        AbstractTag rootTag = null;
    }
}
