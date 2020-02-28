package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata.entrydata;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class StringData implements EntityMetadataEntryData {

    private final String string;

    private StringData(String string) {
        Preconditions.checkNotNull(string, "string cannot be null");
        this.string = string;
    }

    public static StringData of(String string) {
        return new StringData(string);
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
