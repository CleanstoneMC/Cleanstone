package rocks.cleanstone.game.world.chunk.data.block.vanilla.section;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;

public class BlockDataSection {

    public static final int HEIGHT = Chunk.HEIGHT / 16, WIDTH = Chunk.WIDTH;

    private final PaletteBlockStateStorage blockStateStorage;
    private final byte[][][] blockLight, skyLight;
    private final boolean hasSkyLight, writeNonAirBlockCount, omitLighting;

    public BlockDataSection(BlockDataSection blockDataSection) {
        blockStateStorage = new PaletteBlockStateStorage(blockDataSection.blockStateStorage);
        blockLight = blockDataSection.blockLight.clone();
        skyLight = blockDataSection.skyLight.clone();
        hasSkyLight = blockDataSection.hasSkyLight;
        writeNonAirBlockCount = blockDataSection.writesNonAirBlockCount();
        omitLighting = blockDataSection.omitsLighting();
    }

    public BlockDataSection(PaletteBlockStateStorage blockStateStorage, byte[][][] blockLight, byte[][][] skyLight,
                            boolean hasSkyLight, boolean writeNonAirBlockCount, boolean omitLighting) {
        this.blockStateStorage = blockStateStorage;
        this.blockLight = blockLight;
        this.skyLight = skyLight;
        this.hasSkyLight = hasSkyLight;
        this.writeNonAirBlockCount = writeNonAirBlockCount;
        this.omitLighting = omitLighting;
    }

    public BlockDataSection(boolean hasSkyLight, DirectPalette directPalette, boolean omitDirectPaletteLength,
                            boolean writeNonAirBlockCount, boolean omitLighting) {
        this.writeNonAirBlockCount = writeNonAirBlockCount;
        this.omitLighting = omitLighting;
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
                            boolean omitDirectPaletteLength, boolean writeNonAirBlockCount,
                            boolean omitLighting) throws IOException {
        this.writeNonAirBlockCount = writeNonAirBlockCount;
        this.omitLighting = omitLighting;
        this.blockStateStorage = new PaletteBlockStateStorage(in, directPalette, omitDirectPaletteLength);

        this.blockLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.skyLight = new byte[WIDTH][WIDTH][HEIGHT];
        this.hasSkyLight = hasSkyLight;
        if (!omitLighting) {
            readLights(in, true);
            if (hasSkyLight) {
                readLights(in, false);
            }
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
        if (writeNonAirBlockCount)
            out.writeShort(countNonAirBlocks());

        blockStateStorage.write(out);
        if (!omitLighting) {
            writeLights(out, true);
            if (hasSkyLight) writeLights(out, false);
        }
    }

    private void writeLights(ByteBuf buf, boolean isBlockLight) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x += 2) {
                    byte light = isBlockLight ? blockLight[x][z][y] : skyLight[x][z][y];
                    byte upperLight = isBlockLight ? blockLight[x + 1][z][y] : skyLight[x + 1][z][y];
                    byte value = (byte) ((light & 0xff) | (upperLight << 4));
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

    private int countNonAirBlocks() {
        // TODO find less expensive way to calculate non air blocks
        int blockCount = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x++) {
                    BlockState state = blockStateStorage.get(x, y, z);
                    if (state.getBlockType() != VanillaBlockType.AIR) {
                        blockCount++;
                    }
                }
            }
        }
        return blockCount;
    }

    public boolean writesNonAirBlockCount() {
        return writeNonAirBlockCount;
    }

    public boolean omitsLighting() {
        return omitLighting;
    }
}
