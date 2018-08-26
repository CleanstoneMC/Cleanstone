package rocks.cleanstone.data.vanilla.nbt.type;

import rocks.cleanstone.data.vanilla.nbt.TagType;
import rocks.cleanstone.data.vanilla.nbt.VanillaTagType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class StringTag extends AbstractTag<String> {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    private String value;

    public StringTag() {
        super();
    }

    public StringTag(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public AbstractTag loadBuffer(ByteBuffer buffer) throws IOException {
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        this.value = new String(bytes, CHARSET);
        return this;
    }

    @Override
    public void writeToBuffer(ByteBuffer buffer) {
        if (value == null) {
            throw new IllegalStateException("StringTag has no value.");
        }
        buffer.putShort((short) value.length());
        buffer.put(value.getBytes(CHARSET));
    }

    @Override
    public TagType getType() {
        return VanillaTagType.STRING;
    }
}
