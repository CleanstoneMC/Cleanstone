package rocks.cleanstone.net.minecraft.entity.metadata.value;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class FloatValue implements EntityMetadataEntryValue {

    private final float number;

    private FloatValue(float number) {
        this.number = number;
    }

    public static FloatValue of(float number) {
        return new FloatValue(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeFloat(number);
        return byteBuf;
    }
}
