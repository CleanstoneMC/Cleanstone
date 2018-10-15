package rocks.cleanstone.game.world.chunk.data;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ChunkDataKeyFactory {
    private ChunkDataKeyFactory() {
    }

    public static ByteBuf create(ChunkCoords coords, ChunkDataType type) {
        final ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(buf, coords.getX());
        ByteBufUtils.writeVarInt(buf, coords.getZ());
        ByteBufUtils.writeVarInt(buf, type.getTypeID());
        return buf;
    }
}
