package rocks.cleanstone.net.packet.enums;

public enum ObjectType {
    BOAT(1),
    ITEM_STACK(2),
    AREA_EFFECT_CLOUD(3),
    MINECART(10),
    ACTIVATED_TNT(50),
    ENDER_CRYSTAL(51),
    TIPPED_ARROW(60),
    SNOWBALL(61),
    EGG(62),
    FIRE_BALL(63),
    FIRE_CHARGE(64),
    THROWN_ENDERPEARL(65),
    WITHER_SKULL(66),
    SHULKER_BULLET(67),
    LLAMA_SPIT(68),
    FALLING_OBJECTS(70),
    ITEM_FRAMES(71),
    EYE_OF_ENDER(72),
    THROWN_POTION(73),
    THROWN_EXP_BOTTLE(75),
    FIREWORK_ROCKET(76),
    LEASH_KNOT(77),
    ARMOR_STAND(78),
    EVOCATION_FANGS(79),
    FISHING_HOOK(90),
    SPECTRAL_ARROW(91),
    DRAGON_FIREBALL(93);

    private final int typeID;

    ObjectType(int typeID) {
        this.typeID = typeID;
    }

    @SuppressWarnings("Duplicates")
    public static ObjectType fromTypeID(int typeID) {
        for (ObjectType objectType : ObjectType.values()) {
            if (objectType.getTypeID() == typeID) {
                return objectType;
            }
        }

        return null;
    }

    public int getTypeID() {
        return typeID;
    }
}