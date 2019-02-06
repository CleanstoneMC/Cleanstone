package rocks.cleanstone.net.minecraft.entity.metadata.value;

import com.google.common.base.Preconditions;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class StringValue implements EntityMetadataEntryValue {

    private final String string;

    private StringValue(String string) {
        Preconditions.checkNotNull(string, "string cannot be null");
        this.string = string;
    }

    public static StringValue of(String string) {
        return new StringValue(string);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            ByteBufUtils.writeUTF8(byteBuf, string);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while serializing minecraft metadata " +
                    "StringType", e);
        }
        return byteBuf;
    }
}
