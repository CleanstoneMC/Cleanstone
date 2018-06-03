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
package rocks.cleanstone.game.world.region.chunk.vanilla;

import com.google.common.base.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PaletteBlockStorage {

    private static final int MAX_BITS_PER_ENTRY_FOR_USING_PALETTE = 8, GLOBAL_PALETTE_BITS_PER_ENTRY = 13;

    private final List<BlockState> palette;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private int bitsPerEntry;
    private EntrySizeBasedStorage storage;

    public PaletteBlockStorage() {
        this.bitsPerEntry = 4;

        this.palette = new ArrayList<>();
        this.palette.add(BlockState.of(VanillaMaterial.AIR));

        this.storage = new EntrySizeBasedStorage(this.bitsPerEntry, 4096);
    }

    public PaletteBlockStorage(ByteBuf in) throws IOException {
        this.bitsPerEntry = in.readUnsignedByte();
        this.palette = new ArrayList<>();

        int stateCount = ByteBufUtils.readVarInt(in);
        for (int i = 0; i < stateCount; i++) {
            this.palette.add(BlockState.of(ByteBufUtils.readVarInt(in)));
        }
        int amount = ByteBufUtils.readVarInt(in);
        long[] data = new long[amount];
        for (int i = 0; i < amount; amount++) {
            data[i] = in.readLong();
        }
        this.storage = new EntrySizeBasedStorage(this.bitsPerEntry, data);
    }


    private static int index(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }

    public void write(ByteBuf out) {
        out.writeByte(this.bitsPerEntry);

        ByteBufUtils.writeVarInt(out, this.palette.size());
        for (BlockState state : this.palette) {
            ByteBufUtils.writeVarInt(out, state.getRaw());
        }

        long[] data = this.storage.getData();
        ByteBufUtils.writeVarInt(out, data.length);
        for (long dataItem : data) {
            out.writeLong(dataItem);
        }
    }

    public int getBitsPerEntry() {
        return this.bitsPerEntry;
    }

    public List<BlockState> getPalette() {
        return Collections.unmodifiableList(this.palette);
    }

    public EntrySizeBasedStorage getStorage() {
        return this.storage;
    }

    public void set(int x, int y, int z, BlockState state) {
        int id = isUsingPalette() ? this.palette.indexOf(state) : state.getRaw();
        if (id == -1) {
            this.palette.add(state);
            if (this.palette.size() > 1 << this.bitsPerEntry) {
                this.bitsPerEntry++;
                resizeStorage();
            }
            id = isUsingPalette() ? this.palette.indexOf(state) : state.getRaw();
        }

        this.storage.set(index(x, y, z), id);
    }

    private void resizeStorage() {
        List<BlockState> oldStates = this.palette;
        if (!isUsingPalette()) {
            oldStates = new ArrayList<>(this.palette);
            this.palette.clear();
            this.bitsPerEntry = GLOBAL_PALETTE_BITS_PER_ENTRY;
        }

        EntrySizeBasedStorage oldStorage = this.storage;
        this.storage = new EntrySizeBasedStorage(this.bitsPerEntry, this.storage.getSize());
        for (int index = 0; index < this.storage.getSize(); index++) {
            this.storage.set(index, isUsingPalette() ? oldStorage.get(index) : oldStates.get(index).getRaw());
        }
    }

    public boolean isEmpty() {
        for (int index = 0; index < this.storage.getSize(); index++) {
            if (this.storage.get(index) != 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isUsingPalette() {
        return this.bitsPerEntry <= MAX_BITS_PER_ENTRY_FOR_USING_PALETTE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaletteBlockStorage)) return false;

        PaletteBlockStorage that = (PaletteBlockStorage) o;
        return this.bitsPerEntry == that.bitsPerEntry &&
                Objects.equal(this.palette, that.palette) &&
                Objects.equal(this.storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.bitsPerEntry, this.palette, this.storage);
    }
}
