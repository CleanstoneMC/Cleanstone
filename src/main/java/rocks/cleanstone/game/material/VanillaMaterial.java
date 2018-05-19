package rocks.cleanstone.game.material;

public enum VanillaMaterial implements Material {
    STONE(1, "minecraft:stone"),
    CHEST(54, "minecraft:chest");

    private final int id;
    private final String minecraftID;

    VanillaMaterial(int id, String minecraftID) {
        this.id = id;
        this.minecraftID = minecraftID;
    }

    public int getID() {
        return id;
    }

    public String getMinecraftID() {
        return minecraftID;
    }
}
