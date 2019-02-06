package rocks.cleanstone.net.minecraft.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteData implements EntityMetadataEntryData {

    private final byte number;

    private ByteData(byte number) {
        this.number = number;
    }

    public static ByteData of(byte number) {
        return new ByteData(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(number);
        return byteBuf;
    }
}
