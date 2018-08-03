package rocks.cleanstone.game.block;

import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * An immutable state of a block containing its material and metadata
 * <p>
 * Blocks using metadata should extend this class to create a wrapper around the inconvenient metadata field
 */
public class BlockState {

    private static final Map<BlockType, Map<Byte, BlockState>> BLOCK_STATE_CACHE = new ConcurrentHashMap<>();
    private final BlockType blockType;
    private final byte metadata;

    protected BlockState(BlockType blockType, byte metadata) {
        Preconditions.checkNotNull(blockType, "material cannot be null");
        Preconditions.checkArgument(metadata >= 0 && metadata < 16, "metadata out of range");
        this.blockType = blockType;
        this.metadata = metadata;
    }

    public static BlockState of(BlockType blockType, byte metadata) {
        return BLOCK_STATE_CACHE.computeIfAbsent(blockType, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(metadata, k -> new BlockState(blockType, metadata));
    }

    public static BlockState of(BlockType blockType) {
        return of(blockType, (byte) 0);
    }

    public static BlockState of(int rawData, MaterialRegistry materialRegistry) {
        byte metadata = (byte) (rawData & 0xF);
        int blockID = rawData >> 4;

        BlockType blockType = materialRegistry.getBlockType(blockID);
        if (blockType == null) {
            throw new NullPointerException("Cannot find blockType by blockID " + blockID);
        }

        return of(blockType, metadata);
    }

    public final Material getBlockType() {
        return blockType;
    }

    public final byte getMetadata() {
        return metadata;
    }

    public int getRaw() {
        return getBlockType().getID() << 4 | (metadata & 0xF);
    }
}
