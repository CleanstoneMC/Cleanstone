package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import java.io.IOException;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class VanillaBlockDataCodec implements InOutCodec<VanillaBlockDataStorage, ByteBuf> {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final DirectPalette directPalette;
    private final boolean omitDirectPaletteLength;

    public VanillaBlockDataCodec(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory, DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    @Override
    public VanillaBlockDataStorage decode(ByteBuf data) throws IOException {
        BlockDataSection[] sections = new BlockDataSection[Chunk.HEIGHT / BlockDataSection.HEIGHT];
        int primaryBitMask = ByteBufUtils.readVarInt(data);
        int dataSize = ByteBufUtils.readVarInt(data);
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            if ((primaryBitMask & (1 << sectionY)) != 0) {
                BlockDataSection section = new BlockDataSection(data, true, directPalette,
                        omitDirectPaletteLength);
                sections[sectionY] = section;
            }
        }
        return vanillaBlockDataStorageFactory.get(sections, true, directPalette, omitDirectPaletteLength);
    }

    @Override
    public ByteBuf encode(VanillaBlockDataStorage storage) {
        ByteBuf data = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            BlockDataSection section = storage.getSection(sectionY);
            if (section != null) {
                section.write(dataBuf);
                primaryBitMask |= (1 << sectionY);
            }
        }
        for (int z = 0; z < Chunk.WIDTH; z++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                if (!omitDirectPaletteLength)
                    dataBuf.writeByte(127);  // TODO write biome data
                else
                    dataBuf.writeInt(127);
            }
        }

        ByteBufUtils.writeVarInt(data, primaryBitMask);
        ByteBufUtils.writeVarInt(data, dataBuf.readableBytes());
        data.writeBytes(dataBuf);
        ReferenceCountUtil.release(dataBuf);
        return data;
    }
}
