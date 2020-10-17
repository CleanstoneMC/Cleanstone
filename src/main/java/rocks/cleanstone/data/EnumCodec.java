package rocks.cleanstone.data;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

/**
 * A codec for converting enum indexes to/from byte buffers. Enum constants cannot be removed without
 * sacrificing backwards-compatibility, since this is based on the order of the enum constants as they are
 * defined in the enum.
 */
public class EnumCodec<E extends Enum<E>> implements InOutCodec<E, ByteBuf> {

    private final Class<E> enumClass;

    public EnumCodec(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public E decode(ByteBuf data) throws IOException {
        int index = ByteBufUtils.readVarInt(data);
        return enumClass.getEnumConstants()[index];
    }

    @Override
    public ByteBuf encode(E value) {
        ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(buf, value.ordinal());
        return buf;
    }
}
