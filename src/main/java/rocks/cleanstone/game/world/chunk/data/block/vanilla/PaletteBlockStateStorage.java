/*
 * Copyright (C) 2013-2018 Steveice10
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataSection;
import rocks.cleanstone.game.world.chunk.data.block.BlockStateStorage;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PaletteBlockStateStorage implements BlockStateStorage {

    private static final int MAX_BITS_PER_ENTRY_FOR_USING_PALETTE = 8, GLOBAL_PALETTE_BITS_PER_ENTRY = 14;

    private final List<BlockState> palette = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BlockStateMapping<Integer> blockStateMapping;
    private int bitsPerEntry;
    private EntrySizeBasedStorage baseStorage;

    public PaletteBlockStateStorage(PaletteBlockStateStorage paletteBlockStateStorage,
                                    BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
        palette.addAll(paletteBlockStateStorage.palette);
        bitsPerEntry = paletteBlockStateStorage.bitsPerEntry;
        baseStorage = new EntrySizeBasedStorage(paletteBlockStateStorage.baseStorage);
    }

    public PaletteBlockStateStorage(BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
        this.bitsPerEntry = 4;
        this.palette.add(BlockState.of(VanillaBlockType.AIR));
        this.baseStorage = new EntrySizeBasedStorage(this.bitsPerEntry, 4096);
    }

    public PaletteBlockStateStorage(BlockDataTable blockDataTable, int sectionY, AtomicBoolean isEmptyFlag,
                                    BlockStateMapping<Integer> blockStateMapping) {
        this(blockStateMapping);
        isEmptyFlag.set(true);
        int sectionHeight = BlockDataSection.HEIGHT;
        for (int y = sectionY * sectionHeight; y < sectionY * sectionHeight + sectionHeight; y++) {
            for (int z = 0; z < BlockDataSection.WIDTH; z++) {
                for (int x = 0; x < BlockDataSection.WIDTH; x++) {
                    BlockState state = blockDataTable.getBlock(x, y, z).getState();
                    if (state.getBlockType() != VanillaBlockType.AIR) isEmptyFlag.set(false);
                    set(x, y, z, state);
                }
            }
        }
    }

    public PaletteBlockStateStorage(ByteBuf in, BlockStateMapping<Integer> blockStateMapping) throws IOException {
        this.blockStateMapping = blockStateMapping;
        this.bitsPerEntry = in.readUnsignedByte();
        int stateCount = ByteBufUtils.readVarInt(in);

        for (int i = 0; i < stateCount; i++) {
            int blockStateID = ByteBufUtils.readVarInt(in);
            BlockState blockState;
            try {
                blockState = blockStateMapping.getState(blockStateID);
            } catch (NullPointerException e) {
                throw new IOException("Could not find BlockState for ID " + blockStateID, e);
            }

            this.palette.add(blockState);
        }
        int dataAmount = ByteBufUtils.readVarInt(in);
        long[] data = new long[dataAmount];

        for (int i = 0; i < dataAmount; i++) {
            data[i] = in.readLong();
        }
        this.baseStorage = new EntrySizeBasedStorage(this.bitsPerEntry, data);
    }

    @Override
    public void write(ByteBuf out) {
        out.writeByte(this.bitsPerEntry);
        if (this.bitsPerEntry != GLOBAL_PALETTE_BITS_PER_ENTRY)
            ByteBufUtils.writeVarInt(out, this.palette.size());
        for (BlockState state : this.palette) {
            ByteBufUtils.writeVarInt(out, blockStateMapping.getID(state));
        }

        long[] data = this.baseStorage.getData();
        ByteBufUtils.writeVarInt(out, data.length);
        for (long dataItem : data) {
            out.writeLong(dataItem);
        }
    }

    @Override
    public void set(int x, int y, int z, BlockState state) {
        Preconditions.checkNotNull(state, "state cannot be null");
        int stateID = shouldUsePalette() ? this.palette.indexOf(state) : blockStateMapping.getID(state);
        if (stateID == -1) {
            this.palette.add(state);
            // Are the bitsPerEntry too small to store all palette indexes?
            if (this.palette.size() > 1 << this.bitsPerEntry) {
                resizeStorage(++this.bitsPerEntry);
            }
            stateID = shouldUsePalette() ? this.palette.indexOf(state) : blockStateMapping.getID(state);
        }

        this.baseStorage.set(getIndex(x, y, z), stateID);
    }

    @Override
    public BlockState get(int x, int y, int z) {
        int stateID = baseStorage.get(getIndex(x, y, z));
        if (stateID == 0) return BlockState.of(VanillaBlockType.AIR);
        return shouldUsePalette() ? this.palette.get(stateID) : blockStateMapping.getState(stateID);
    }

    private void resizeStorage(int bitsPerEntry) {
        List<BlockState> previousPalette = this.palette;
        if (!shouldUsePalette()) {
            previousPalette = new ArrayList<>(this.palette);
            this.palette.clear();
            this.bitsPerEntry = GLOBAL_PALETTE_BITS_PER_ENTRY;
        }

        EntrySizeBasedStorage previousStorage = this.baseStorage;
        this.baseStorage = new EntrySizeBasedStorage(this.bitsPerEntry, this.baseStorage.getSize());
        for (int index = 0; index < this.baseStorage.getSize(); index++) {
            this.baseStorage.set(index, shouldUsePalette() ? previousStorage.get(index)
                    : blockStateMapping.getID(previousPalette.get(index)));
        }
    }

    private int getIndex(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }

    private boolean shouldUsePalette() {
        return this.bitsPerEntry <= MAX_BITS_PER_ENTRY_FOR_USING_PALETTE;
    }

    public int getBitsPerEntry() {
        return this.bitsPerEntry;
    }

    public List<BlockState> getPalette() {
        return ImmutableList.copyOf(this.palette);
    }

    public EntrySizeBasedStorage getBaseStorage() {
        return this.baseStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaletteBlockStateStorage)) return false;

        PaletteBlockStateStorage that = (PaletteBlockStateStorage) o;
        return this.bitsPerEntry == that.bitsPerEntry &&
                Objects.equal(this.palette, that.palette) &&
                Objects.equal(this.baseStorage, that.baseStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.bitsPerEntry, this.palette, this.baseStorage);
    }
}
