package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BooleanData implements EntityMetadataEntryData {

    private final boolean state;

    private BooleanData(boolean state) {
        this.state = state;
    }

    public static BooleanData of(boolean state) {
        return new BooleanData(state);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(state);
        return byteBuf;
    }
}
