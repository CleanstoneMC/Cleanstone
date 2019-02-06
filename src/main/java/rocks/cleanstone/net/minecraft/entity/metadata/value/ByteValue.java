package rocks.cleanstone.net.minecraft.entity.metadata.value;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteValue implements EntityMetadataEntryValue {

    private final byte number;

    private ByteValue(byte number) {
        this.number = number;
    }

    public static ByteValue of(byte number) {
        return new ByteValue(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(number);
        return byteBuf;
    }
}
