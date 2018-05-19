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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class BlockStorage {

    private int bitsPerEntry;

    private List<BlockState> states;
    private FlexibleStorage storage;

    public BlockStorage() {
        this.bitsPerEntry = 4;

        this.states = new ArrayList<>();
        this.states.add(null);

        this.storage = new FlexibleStorage(this.bitsPerEntry, 4096);
    }

    private static int index(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }

    private static int stateToRaw(@Nullable BlockState state) {
        int blockID = state != null ? state.getMaterial().getID() : 0;
        byte metadata = state != null ? state.getMetadata() : 0;
        return blockID << 4 | (metadata & 0xF);
    }

    public void write(ByteBuf buf) {
        buf.writeByte(this.bitsPerEntry);

        ByteBufUtils.writeVarInt(buf, this.states.size());
        for (BlockState state : this.states) {
            ByteBufUtils.writeVarInt(buf, stateToRaw(state));
        }

        long[] data = this.storage.getData();
        ByteBufUtils.writeVarInt(buf, data.length);
        for (long dataItem : data) {
            buf.writeLong(dataItem);
        }
    }

    public int getBitsPerEntry() {
        return this.bitsPerEntry;
    }

    public List<BlockState> getStates() {
        return Collections.unmodifiableList(this.states);
    }

    public FlexibleStorage getStorage() {
        return this.storage;
    }

    public void set(int x, int y, int z, BlockState state) {
        int id = this.bitsPerEntry <= 8 ? this.states.indexOf(state) : stateToRaw(state);
        if (id == -1) {
            this.states.add(state);
            if (this.states.size() > 1 << this.bitsPerEntry) {
                this.bitsPerEntry++;

                List<BlockState> oldStates = this.states;
                if (this.bitsPerEntry > 8) {
                    oldStates = new ArrayList<>(this.states);
                    this.states.clear();
                    this.bitsPerEntry = 13;
                }

                FlexibleStorage oldStorage = this.storage;
                this.storage = new FlexibleStorage(this.bitsPerEntry, this.storage.getSize());
                for (int index = 0; index < this.storage.getSize(); index++) {
                    this.storage.set(index, this.bitsPerEntry <= 8 ? oldStorage.get(index) : stateToRaw(oldStates.get(index)));
                }
            }

            id = this.bitsPerEntry <= 8 ? this.states.indexOf(state) : stateToRaw(state);
        }

        this.storage.set(index(x, y, z), id);
    }

    public boolean isEmpty() {
        for (int index = 0; index < this.storage.getSize(); index++) {
            if (this.storage.get(index) != 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockStorage)) return false;

        BlockStorage that = (BlockStorage) o;
        return this.bitsPerEntry == that.bitsPerEntry &&
                Objects.equal(this.states, that.states) &&
                Objects.equal(this.storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.bitsPerEntry, this.states, this.storage);
    }
}