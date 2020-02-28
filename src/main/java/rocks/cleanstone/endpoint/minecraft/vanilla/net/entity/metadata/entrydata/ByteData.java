package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata.entrydata;

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

    public static ByteData fromBits(boolean... bits) {
        byte number = 0;
        for (int i = 0; i < bits.length; i++) {
            boolean bit = bits[i];
            number += bit ? (1 << i) : 0;
        }
        return of(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(number);
        return byteBuf;
    }
}
