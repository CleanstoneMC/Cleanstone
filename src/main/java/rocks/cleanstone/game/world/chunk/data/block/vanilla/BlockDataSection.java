package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.world.chunk.Chunk;

import java.io.IOException;

public class BlockDataSection {

    public static final int HEIGHT = Chunk.HEIGHT / 16, WIDTH = Chunk.WIDTH;

    private final PaletteBlockStateStorage blockStateStorage;
    private final byte[][][] blockLight, skyLight;
    private final boolean hasSkyLight;

    public BlockDataSection(BlockDataSection blockDataSection) {
        blockStateStorage = new PaletteBlockStateStorage(blockDataSection.blockStateStorage);
        blockLight = blockDataSection.blockLight.clone();
        skyLight = blockDataSection.skyLight.clone();
        hasSkyLight = blockDataSection.hasSkyLight;
    }

    public BlockDataSection(PaletteBlockStateStorage blockStateStorage, byte[][][] blockLight, byte[][][] skyLight,
                            boolean hasSkyLight) {
        this.blockStateStorage = blockStateStorage;
        this.blockLight = blockLight;
        this.skyLight = skyLight;
        this.hasSkyLight = hasSkyLight;
    }

    public BlockDataSection(boolean hasSkyLight, DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.blockStateStorage = new PaletteBlockStateStorage(directPalette, omitDirectPaletteLength);
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

    public BlockDataSection(ByteBuf in, boolean hasSkyLight, DirectPalette directPalette,
                            boolean omitDirectPaletteLength) throws IOException {
        this.blockStateStorage = new PaletteBlockStateStorage(in, directPalette, omitDirectPaletteLength);

        this.blockLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.skyLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.hasSkyLight = hasSkyLight;
        readLights(in, true);
        if (hasSkyLight) {
            readLights(in, false);
        }
    }

    public PaletteBlockStateStorage getBlockStateStorage() {
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
                    final byte light = isBlockLight ? blockLight[x][z][y] : skyLight[x][z][y];
                    final byte upperLight = isBlockLight ? blockLight[x + 1][z][y] : skyLight[x + 1][z][y];
                    final byte value = (byte) (light | (upperLight << 4));
                    buf.writeByte(value);
                }
            }
        }
    }

    private void readLights(ByteBuf buf, boolean isBlockLight) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x += 2) {
                    final byte value = buf.readByte();
                    final byte light = (byte) (value & 0xF);
                    final byte upperLight = (byte) ((value >> 4) & 0xF);
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
