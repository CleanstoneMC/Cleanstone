package rocks.cleanstone.game.block;

import java.util.Collection;
import java.util.HashSet;

import rocks.cleanstone.game.material.Material;

/**
 * An immutable state of a block containing its material and data
 */
public class BlockState {

    private static final Collection<BlockState> CACHED_STATES = new HashSet<>();

    private final Material material;
    private final byte data;

    private BlockState(Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public static BlockState of(Material material, byte data) {
        return CACHED_STATES.stream().filter(b -> b.getMaterial().equals(material) && b.getData() == data)
                .findFirst().orElseGet(() -> {
                    BlockState newState = new BlockState(material, (byte) data);
                    CACHED_STATES.add(newState);
                    return newState;
                });
    }

    public static BlockState of(Material material) {
        return of(material, (byte) 0);
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }
}
