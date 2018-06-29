package rocks.cleanstone.game.world.region.chunk.data.block;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class BlockDataCodec implements Codec<BlockDataStorage, ByteBuf> {

    @Override
    public BlockDataStorage deserialize(ByteBuf data) throws IOException {
        Map<Integer, BlockDataSection> sectionMap = new HashMap<>();
        int primaryBitMask = ByteBufUtils.readVarInt(data);
        int dataSize = ByteBufUtils.readVarInt(data);
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            if ((primaryBitMask & (1 << sectionY)) != 0) {
                BlockDataSection section = new BlockDataSection(data, true);
                sectionMap.put(sectionY, section);
            }
        }
        return new BlockDataStorage(sectionMap, true);
    }

    @Override
    public ByteBuf serialize(BlockDataStorage storage) {
        ByteBuf data = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            BlockDataSection section = storage.getSectionMap().get(sectionY);
            if (section != null) {
                section.write(dataBuf);
                primaryBitMask |= (1 << sectionY);
            }
        }
        for (int z = 0; z < Chunk.WIDTH; z++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                dataBuf.writeByte(127);  // TODO write biome data
            }
        }

        ByteBufUtils.writeVarInt(data, primaryBitMask);
        ByteBufUtils.writeVarInt(data, dataBuf.readableBytes());
        data.writeBytes(dataBuf);
        ReferenceCountUtil.release(dataBuf);
        return data;
    }
}
