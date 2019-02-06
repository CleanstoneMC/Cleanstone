package rocks.cleanstone.net.minecraft.entity.metadata.value;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BooleanValue implements EntityMetadataEntryValue {

    private final boolean state;

    private BooleanValue(boolean state) {
        this.state = state;
    }

    public static BooleanValue of(boolean state) {
        return new BooleanValue(state);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(state);
        return byteBuf;
    }
}
