package rocks.cleanstone.net.minecraft.entity.metadata;

/**
 * A collection of valid {@link EntityMetadataEntry} IDs as defined in the protocol entity schemes
 */
public enum VanillaEntityMetadataEntryID {
    ENTITY_BITS("entityBits"),
    AIR_AMOUNT("airAmount"),
    CUSTOM_NAME("customName"),
    CUSTOM_NAME_VISIBLE("customNameVisible"),
    SILENT("silent"),
    NO_GRAVITY("noGravity"),
    HAND_STATE("handState"),
    HEALTH("health"),
    POTION_EFFECT_COLOR("potionEffectColor"),
    POTION_EFFECT_AMBIENT("potionEffectAmbient"),
    ARROW_AMOUNT("arrowAmount"),
    MOB_BITS("mobBits"),
    IS_BABY("isBaby"),
    SHEEP_BITS("sheepBits"),
    BLAZE_BITS("blazeBits"),
    CREEPER_STATE("creeperState"),
    CHARGED("charged"),
    IGNITED("ignited");

    private final String name;

    VanillaEntityMetadataEntryID(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
