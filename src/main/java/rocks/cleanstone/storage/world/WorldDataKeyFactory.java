package rocks.cleanstone.storage.world;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.storage.chunk.ChunkDataType;

public class WorldDataKeyFactory {
    private WorldDataKeyFactory() {
    }

    public static ByteBuf create(WorldDataType type) {
        ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeVarInt(buf, type.getTypeID());
        return buf;
    }

    public static ByteBuf createForChunk(ChunkCoords coords, ChunkDataType type) {
        ByteBuf buf = create(StandardWorldDataType.CHUNK_DATA);
        ByteBufUtils.writeVarInt(buf, coords.getX());
        ByteBufUtils.writeVarInt(buf, coords.getZ());
        ByteBufUtils.writeVarInt(buf, type.getTypeID());
        return buf;
    }
}
