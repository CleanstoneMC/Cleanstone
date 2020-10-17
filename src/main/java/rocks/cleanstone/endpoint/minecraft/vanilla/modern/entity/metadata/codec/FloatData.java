package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class FloatData implements EntityMetadataEntryData {

    private final float number;

    private FloatData(float number) {
        this.number = number;
    }

    public static FloatData of(float number) {
        return new FloatData(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeFloat(number);
        return byteBuf;
    }
}
