package rocks.cleanstone.game.block;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.Collection;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * An immutable state of a block containing its material and metadata
 * <p>
 * Blocks using metadata should extend this class to create a wrapper around the inconvenient metadata field
 */
public class BlockState {

    private static final Collection<BlockState> CACHED_STATES = Sets.newConcurrentHashSet();

    private final BlockType blockType;
    private final byte metadata;

    protected BlockState(BlockType blockType, byte metadata) {
        Preconditions.checkNotNull(blockType, "material cannot be null");
        Preconditions.checkArgument(metadata >= 0 && metadata < 16, "metadata out of range");
        this.blockType = blockType;
        this.metadata = metadata;
    }

    public static BlockState of(BlockType blockType, byte metadata) {
        return CACHED_STATES.stream().filter(b -> b.getBlockType().equals(blockType) && b.getMetadata() == metadata)
                .findFirst().orElseGet(() -> {
                    BlockState newState = new BlockState(blockType, metadata);
                    CACHED_STATES.add(newState);
                    return newState;
                });
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
