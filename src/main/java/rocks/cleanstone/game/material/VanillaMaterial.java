package rocks.cleanstone.game.material;

public enum VanillaMaterial implements Material {
    STONE(1, "minecraft:stone"),
    CHEST(54, "minecraft:chest");

    private final int blockID;
    private final String minecraftID;

    VanillaMaterial(int blockID, String minecraftID) {
        this.blockID = blockID;
        this.minecraftID = minecraftID;
    }

    public int getID() {
        return blockID;
    }

    public String getMinecraftID() {
        return minecraftID;
    }
}
