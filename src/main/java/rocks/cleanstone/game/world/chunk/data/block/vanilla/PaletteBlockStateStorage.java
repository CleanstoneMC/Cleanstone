package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PaletteBlockStateStorage {

    private static final int MINIMUM_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE = 4,
            MAX_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE = 8;

    private final DirectPalette directPalette;
    private final List<BlockState> indirectPalette;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final boolean omitDirectPaletteLength;
    private int bitsPerEntry;
    private EntrySizeBasedStorage baseStorage;

    public PaletteBlockStateStorage(DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.directPalette = directPalette;
        this.bitsPerEntry = MINIMUM_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE;
        this.indirectPalette = new ArrayList<>();
        indirectPalette.add(BlockState.of(VanillaBlockType.AIR));
        this.baseStorage = new EntrySizeBasedStorage(bitsPerEntry, 4096);
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    public PaletteBlockStateStorage(BlockDataTable blockDataTable, int sectionY, AtomicBoolean isEmptyFlag,
                                    DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this(directPalette, omitDirectPaletteLength);
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

    public PaletteBlockStateStorage(PaletteBlockStateStorage paletteBlockStateStorage) {
        this.directPalette = paletteBlockStateStorage.directPalette;
        this.indirectPalette = new ArrayList<>(paletteBlockStateStorage.indirectPalette);
        this.bitsPerEntry = paletteBlockStateStorage.bitsPerEntry;
        this.baseStorage = new EntrySizeBasedStorage(paletteBlockStateStorage.baseStorage);
        this.omitDirectPaletteLength = paletteBlockStateStorage.omitDirectPaletteLength;
    }

    public PaletteBlockStateStorage(ByteBuf in, DirectPalette directPalette,
                                    boolean omitDirectPaletteLength) throws IOException {
        this.directPalette = directPalette;
        this.indirectPalette = new ArrayList<>();

        this.bitsPerEntry = in.readUnsignedByte();
        int indirectPaletteLength = (isIndirectPalette(bitsPerEntry) || !omitDirectPaletteLength)
                ? ByteBufUtils.readVarInt(in) : 0;

        for (int i = 0; i < indirectPaletteLength; i++) {
            int stateID = ByteBufUtils.readVarInt(in);
            BlockState blockState = directPalette.getBlockState(stateID);
            indirectPalette.add(blockState);
        }
        int dataLength = ByteBufUtils.readVarInt(in);
        long[] data = new long[dataLength];

        for (int i = 0; i < dataLength; i++) {
            data[i] = in.readLong();
        }
        this.baseStorage = new EntrySizeBasedStorage(bitsPerEntry, data);
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    public void write(ByteBuf out) {
        out.writeByte(bitsPerEntry);
        if (isIndirectPalette(bitsPerEntry) || !omitDirectPaletteLength) {
            ByteBufUtils.writeVarInt(out, indirectPalette.size());
        }
        for (BlockState state : indirectPalette) {
            ByteBufUtils.writeVarInt(out, directPalette.getIndex(state));
        }

        long[] data = baseStorage.getData();
        ByteBufUtils.writeVarInt(out, data.length);
        for (long dataItem : data) {
            out.writeLong(dataItem);
        }
    }

    public void set(int x, int y, int z, BlockState state) {
        Preconditions.checkNotNull(state, "state cannot be null");
        int paletteIndex = getPaletteIndex(state);
        if (paletteIndex == -1) {
            indirectPalette.add(state);
            // Are the bitsPerEntry too small to store all palette indexes?
            if (indirectPalette.size() > 1 << bitsPerEntry) {
                int bitsPerEntry = isIndirectPalette(this.bitsPerEntry + 1) ?
                        this.bitsPerEntry + 1 : directPalette.getBitsPerEntry();
                resizeBaseStorage(bitsPerEntry);
            }
            paletteIndex = getPaletteIndex(state);
        }

        baseStorage.set(getPositionIndex(x, y, z), paletteIndex);
    }

    public BlockState get(int x, int y, int z) {
        int paletteIndex = baseStorage.get(getPositionIndex(x, y, z));
        if (paletteIndex == 0) return BlockState.of(VanillaBlockType.AIR);
        return getBlockState(paletteIndex);
    }

    private void resizeBaseStorage(int bitsPerEntry) {
        this.bitsPerEntry = bitsPerEntry;

        EntrySizeBasedStorage resizedStorage = new EntrySizeBasedStorage(bitsPerEntry, baseStorage.getSize());
        for (int i = 0; i < baseStorage.getSize(); i++) {
            resizedStorage.set(i, isIndirectPalette(bitsPerEntry) ? baseStorage.get(i)
                    : directPalette.getIndex(indirectPalette.get(i)));
        }
        baseStorage = resizedStorage;

        if (!isIndirectPalette(bitsPerEntry)) {
            indirectPalette.clear();
        }
    }

    private int getPaletteIndex(BlockState state) {
        return isIndirectPalette(bitsPerEntry) ? indirectPalette.indexOf(state)
                : directPalette.getIndex(state);
    }

    private BlockState getBlockState(int paletteIndex) {
        return isIndirectPalette(bitsPerEntry) ? indirectPalette.get(paletteIndex)
                : directPalette.getBlockState(paletteIndex);
    }

    private int getPositionIndex(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }

    private boolean isIndirectPalette(int bitsPerEntry) {
        return bitsPerEntry <= MAX_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaletteBlockStateStorage)) return false;
        PaletteBlockStateStorage that = (PaletteBlockStateStorage) o;
        return omitDirectPaletteLength == that.omitDirectPaletteLength &&
                bitsPerEntry == that.bitsPerEntry &&
                Objects.equal(directPalette, that.directPalette) &&
                Objects.equal(indirectPalette, that.indirectPalette) &&
                Objects.equal(baseStorage, that.baseStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(directPalette, indirectPalette, omitDirectPaletteLength,
                bitsPerEntry, baseStorage);
    }
}
