package rocks.cleanstone.game;

import rocks.cleanstone.game.world.region.block.Block;
import rocks.cleanstone.game.world.region.block.vanilla.SolidBlock;

public enum VanillaMaterial implements Material {
    STONE(1, "minecraft:stone", SolidBlock.class),
    SMOOTH_STONE(1, "minecraft:smooth_stone", SolidBlock.class);

    private final int blockID;
    private final String minecraftID;
    private final Class<? extends Block> blockClass;

    VanillaMaterial(int blockID, String minecraftID, Class<? extends Block> blockClass) {
        this.blockID = blockID;
        this.minecraftID = minecraftID;
        this.blockClass = blockClass;
    }

    public int getBlockID() {
        return blockID;
    }

    public String getMinecraftID() {
        return minecraftID;
    }

    public Class<? extends Block> getBlockClass() {
        return blockClass;
    }
}
