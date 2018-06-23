package rocks.cleanstone.game.block;

import com.google.common.base.Preconditions;
import io.netty.util.internal.ConcurrentSet;
import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.VanillaMaterial;

import java.util.Collection;
import java.util.HashSet;

/**
 * An immutable state of a block containing its material and metadata
 * <p>
 * Blocks using metadata should extend this class to create a wrapper around the inconvenient metadata field
 */
public class BlockState {

    private static final Collection<BlockState> CACHED_STATES = new ConcurrentSet<>();

    private final Material material;
    private final byte metadata;

    protected BlockState(Material material, byte metadata) {
        Preconditions.checkNotNull(material, "material cannot be null");
        Preconditions.checkArgument(metadata >= 0 && metadata < 16, "metadata out of range");
        this.material = material;
        this.metadata = metadata;
    }

    public static BlockState of(Material material, byte metadata) {
        return CACHED_STATES.stream().filter(b -> b.getMaterial().equals(material) && b.getMetadata() == metadata)
                .findFirst().orElseGet(() -> {
                    BlockState newState = new BlockState(material, metadata);
                    CACHED_STATES.add(newState);
                    return newState;
                });
    }

    public static BlockState of(Material material) {
        return of(material, (byte) 0);
    }

    public static BlockState of(int rawData) {
        byte metadata = (byte) (rawData & 0xF);
        int blockID = rawData >> 4;
        return of(VanillaMaterial.byID(blockID), metadata);
    }

    public final Material getMaterial() {
        return material;
    }

    public final byte getMetadata() {
        return metadata;
    }

    public int getRaw() {
        return getMaterial().getID() << 4 | (metadata & 0xF);
    }
}
