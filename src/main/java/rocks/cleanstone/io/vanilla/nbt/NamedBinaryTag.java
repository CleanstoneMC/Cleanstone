package rocks.cleanstone.io.vanilla.nbt;

import java.io.File;

/**
 * Coded by fionera.
 */
public class NamedBinaryTag {

    private final File file;

    public NamedBinaryTag(String fileName) {
        this.file = new File(fileName);
    }

    public NamedBinaryTag(File file) {
        this.file = file;
    }
}
