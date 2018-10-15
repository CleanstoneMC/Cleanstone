package rocks.cleanstone.net.minecraft.packet.enums;

public enum LevelType {
    DEFAULT("default"),
    FLAT("flat"),
    LARGE_BIOMES("largeBiomes"),
    AMPLIFIED("amplified"),
    DEFAULT_1_1("default_1_1");

    private final String levelType;

    LevelType(String levelType) {
        this.levelType = levelType;
    }

    @SuppressWarnings("Duplicates")
    public static LevelType fromLevelType(String levelType) {
        for (final LevelType type : LevelType.values()) {
            if (type.getLevelType().equals(levelType)) {
                return type;
            }
        }

        return null;
    }

    public String getLevelType() {
        return levelType;
    }
}
