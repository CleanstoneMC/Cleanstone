package rocks.cleanstone.data.vanilla.nbt;

import java.nio.file.Path;
import java.nio.file.Paths;
import rocks.cleanstone.data.vanilla.nbt.type.AbstractTag;

public class NamedBinaryTag {

    private final Path path;
    private final AbstractTag rootTag;
    private String rootTagId;
    private byte rootTagType;

    public NamedBinaryTag(String fileName) {
        this(Paths.get(fileName));
    }

    public NamedBinaryTag(Path path) {
        this.path = path;
        // TODO decompress from GZip if needed and read root tag
        rootTag = null;
    }
}
