package rocks.cleanstone.net.minecraft.entity.metadata.value;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class VarIntValue implements EntityMetadataEntryValue {

    private final int number;

    private VarIntValue(int number) {
        this.number = number;
    }

    public static VarIntValue of(int number) {
        return new VarIntValue(number);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(byteBuf, number);
        return byteBuf;
    }
}
