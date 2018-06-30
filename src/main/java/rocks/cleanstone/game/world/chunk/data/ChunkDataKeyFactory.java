package rocks.cleanstone.game.world.chunk.data;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ChunkDataKeyFactory {
    private ChunkDataKeyFactory() {
    }

    public static ByteBuf create(int x, int y, ChunkDataType type) {
        ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(buf, x);
        ByteBufUtils.writeVarInt(buf, y);
        ByteBufUtils.writeVarInt(buf, type.getTypeID());
        return buf;
    }
}
