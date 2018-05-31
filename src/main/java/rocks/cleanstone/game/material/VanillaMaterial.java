package rocks.cleanstone.game.material;

import javax.annotation.Nullable;
import java.util.Arrays;

public enum VanillaMaterial implements Material {
    AIR(0, "minecraft:air"),
    STONE(1, "minecraft:stone"),
    GRASS(2, "minecraft:grass"),
    DIRT(3, "minecraft:dirt"),
    BEDROCK(7, "minecraft:bedrock"),
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

    @Nullable
    public static VanillaMaterial byID(int id) {
        return Arrays.stream(values()).filter(material -> material.getID() == id).findAny().orElse(null);
    }
}
