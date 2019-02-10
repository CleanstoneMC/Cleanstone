package rocks.cleanstone.net.minecraft.entity;

import javax.annotation.Nullable;

/**
 * The various types of "mob" entities that are known to the minecraft client
 */
public enum VanillaMobType {
    BAT("bat"),
    BLAZE("blaze"),
    CAVE_SPIDER("cave_spider"),
    CHICKEN("chicken"),
    COD("cod"),
    CREEPER("creeper"),
    SHEEP("sheep");

    private final String name;

    VanillaMobType(String name) {
        this.name = name;
    }

    @Nullable
    public static VanillaMobType fromName(String name) {
        for (VanillaMobType entityType : VanillaMobType.values()) {
            if (entityType.getName().equals(name)) {
                return entityType;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
