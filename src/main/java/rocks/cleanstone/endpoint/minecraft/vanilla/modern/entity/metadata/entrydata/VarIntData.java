package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class VarIntData implements EntityMetadataEntryData {

    private final int number;

    private VarIntData(int number) {
        this.number = number;
    }

    public static VarIntData of(int number) {
        return new VarIntData(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(byteBuf, number);
        return byteBuf;
    }
}
