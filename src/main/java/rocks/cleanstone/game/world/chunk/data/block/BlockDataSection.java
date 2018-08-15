package rocks.cleanstone.game.world.chunk.data.block;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.PaletteBlockStateStorage;

import java.io.IOException;

public class BlockDataSection {

    public static final int HEIGHT = Chunk.HEIGHT / 16, WIDTH = Chunk.WIDTH;

    private final BlockStateStorage blockStateStorage;
    private final byte[][][] blockLight, skyLight;
    private final boolean hasSkyLight;

    public BlockDataSection(BlockDataSection blockDataSection, BlockStateMapping<Integer> blockStateMapping) {
        blockStateStorage = new PaletteBlockStateStorage(
                (PaletteBlockStateStorage) blockDataSection.blockStateStorage, blockStateMapping);
        blockLight = blockDataSection.blockLight.clone();
        skyLight = blockDataSection.skyLight.clone();
        hasSkyLight = blockDataSection.hasSkyLight;
    }

    public BlockDataSection(BlockStateStorage blockStateStorage, byte[][][] blockLight, byte[][][] skyLight,
                            boolean hasSkyLight) {
        this.blockStateStorage = blockStateStorage;

        this.blockLight = blockLight;
        this.skyLight = skyLight;
        this.hasSkyLight = hasSkyLight;
    }

    public BlockDataSection(boolean hasSkyLight, BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateStorage = new PaletteBlockStateStorage(blockStateMapping);
        blockLight = new byte[WIDTH][WIDTH][HEIGHT];
        skyLight = new byte[WIDTH][WIDTH][HEIGHT];
        if (hasSkyLight)
            for (int y = 0; y < HEIGHT; y++) {
                for (int z = 0; z < WIDTH; z++) {
                    for (int x = 0; x < WIDTH; x++) {
                        skyLight[x][z][y] = 15;
                    }
                }
            }
        this.hasSkyLight = hasSkyLight;
    }

    public BlockDataSection(ByteBuf in, boolean hasSkyLight, BlockStateMapping<Integer> blockStateMapping) throws IOException {
        blockStateStorage = new PaletteBlockStateStorage(in, blockStateMapping);

        this.blockLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.skyLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.hasSkyLight = hasSkyLight;
        readLights(in, true);
        if (hasSkyLight) {
            readLights(in, false);
        }
    }

    public BlockStateStorage getBlockStateStorage() {
        return blockStateStorage;
    }

    public byte[][][] getBlockLight() {
        return blockLight;
    }

    public byte[][][] getSkyLight() {
        return skyLight;
    }

    public boolean hasSkyLight() {
        return hasSkyLight;
    }

    public void write(ByteBuf out) {
        blockStateStorage.write(out);
        writeLights(out, true);
        if (hasSkyLight) writeLights(out, false);
    }

    private void writeLights(ByteBuf buf, boolean isBlockLight) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x += 2) {
                    byte light = isBlockLight ? blockLight[x][z][y] : skyLight[x][z][y];
                    byte upperLight = isBlockLight ? blockLight[x + 1][z][y] : skyLight[x + 1][z][y];
                    byte value = (byte) (light | (upperLight << 4));
                    buf.writeByte(value);
                }
            }
        }
    }

    private void readLights(ByteBuf buf, boolean isBlockLight) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x += 2) {
                    byte value = buf.readByte();
                    byte light = (byte) (value & 0xF);
                    byte upperLight = (byte) ((value >> 4) & 0xF);
                    if (isBlockLight) {
                        blockLight[x][z][y] = light;
                        blockLight[x + 1][z][y] = upperLight;
                    } else {
                        skyLight[x][z][y] = light;
                        skyLight[x + 1][z][y] = upperLight;
                    }
                }
            }
        }
    }
}
