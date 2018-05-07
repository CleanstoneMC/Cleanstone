package rocks.cleanstone.game;

public enum VanillaMaterial implements Material {
    STONE(1, "minecraft:stone"),
    SMOOTH_STONE(1, "minecraft:smooth_stone");

    private final int blockID;
    private final String minecraftID;

    VanillaMaterial(int blockID, String minecraftID) {
        this.blockID = blockID;
        this.minecraftID = minecraftID;
    }

    public int getBlockID() {
        return blockID;
    }

    public String getMinecraftID() {
        return minecraftID;
    }
}
