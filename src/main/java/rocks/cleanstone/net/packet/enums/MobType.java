package rocks.cleanstone.net.packet.enums;

public enum MobType {
    ITEM(1, "minecraft:item"),
    XP_ORB(2, "minecraft:xp_orb"),
    AREA_EFFECT_CLOUD(3, "minecraft:area_effect_cloud"),
    ELDER_GUARDIAN(4, "minecraft:elder_guardian"),
    WITHER_SKELETON(5, "minecraft:wither_skeleton"),
    STRAY(6, "minecraft:stray"),
    THROWN_EGG(7, "minecraft:egg"),
    LEASH_KNOT(8, "minecraft:leash_knot"),
    PAINTING(9, "minecraft:painting"),
    ARROW(10, "minecraft:arrow"),
    SNOWBALL(11, "minecraft:snowball"),
    FIREBALL(12, "minecraft:fireball"),
    SMALL_FIREBALL(13, "minecraft:small_fireball"),
    THROWN_ENDERPEARL(14, "minecraft:ender_pearl"),
    EYE_OF_ENDER_SIGNAL(15, "minecraft:eye_of_ender_signal"),
    THROWN_POTION(16, "minecraft:potion"),
    THROWN_EXP_BOTTLE(17, "minecraft:xp_bottle"),
    ITEM_FRAME(18, "minecraft:item_frame"),
    WITHER_SKULL(19, "minecraft:wither_skull"),
    PRIMED_TNT(20, "minecraft:tnt"),
    FALLING_SAND(21, "minecraft:falling_block"),
    FIREWORKS_ROCKET_ENTITY(22, "minecraft:fireworks_rocket"),
    HUSK(23, "minecraft:husk"),
    SPECTRAL_ARROW(24, "minecraft:spectral_arrow"),
    SHULKER_BULLET(25, "minecraft:shulker_bullet"),
    DRAGON_FIREBALL(26, "minecraft:dragon_fireball"),
    ZOMBIE_VILLAGER(27, "minecraft:zombie_villager"),
    SKELETON_HORSE(28, "minecraft:skeleton_horse"),
    ZOMBIE_HORSE(29, "minecraft:zombie_horse"),
    ARMOR_STAND(30, "minecraft:armor_stand"),
    DONKEY(31, "minecraft:donkey"),
    MULE(32, "minecraft:mule"),
    EVOCATION_FANGS(33, "minecraft:evocation_fangs"),
    EVOCATION_ILLAGER(34, "minecraft:evocation_illager"),
    VEX(35, "minecraft:vex"),
    VINDICATION_ILLAGER(36, "minecraft:vindication_illager"),
    ILLUSION_ILLAGER(37, "minecraft:illusion_illager"),
    MINECART_COMMANDBLOCK(40, "minecraft:commandblock_minecart"),
    BOAT(41, "minecraft:boat"),
    MINECART_RIDEABLE(42, "minecraft:minecart"),
    MINECART_CHEST(43, "minecraft:chest_minecart"),
    MINECART_FURNACE(44, "minecraft:furnace_minecart"),
    MINECART_TNT(45, "minecraft:tnt_minecart"),
    MINECART_HOPPER(46, "minecraft:hopper_minecart"),
    MINECART_SPAWNER(47, "minecraft:spawner_minecart"),
    CREEPER(50, "minecraft:creeper"),
    SKELETON(51, "minecraft:skeleton"),
    SPIDER(52, "minecraft:spider"),
    GIANT(53, "minecraft:giant"),
    ZOMBIE(54, "minecraft:zombie"),
    SLIME(55, "minecraft:slime"),
    GHAST(56, "minecraft:ghast"),
    PIG_ZOMBIE(57, "minecraft:zombie_pigman"),
    ENDERMAN(58, "minecraft:enderman"),
    CAVE_SPIDER(59, "minecraft:cave_spider"),
    SILVERFISH(60, "minecraft:silverfish"),
    BLAZE(61, "minecraft:blaze"),
    LAVASLIME(62, "minecraft:magma_cube"),
    ENDER_DRAGON(63, "minecraft:ender_dragon"),
    WITHER_BOSS(64, "minecraft:wither"),
    BAT(65, "minecraft:bat"),
    WITCH(66, "minecraft:witch"),
    ENDERMITE(67, "minecraft:endermite"),
    GUARDIAN(68, "minecraft:guardian"),
    SHULKER(69, "minecraft:shulker"),
    PIG(90, "minecraft:pig"),
    SHEEP(91, "minecraft:sheep"),
    COW(92, "minecraft:cow"),
    CHICKEN(93, "minecraft:chicken"),
    SQUID(94, "minecraft:squid"),
    WOLF(95, "minecraft:wolf"),
    MUSHROOMCOW(96, "minecraft:mooshroom"),
    SNOWMAN(97, "minecraft:snowman"),
    OZELOT(98, "minecraft:ocelot"),
    VILLAGER_GOLEM(99, "minecraft:villager_golem"),
    HORSE(100, "minecraft:horse"),
    RABBIT(101, "minecraft:rabbit"),
    POLAR_BEAR(102, "minecraft:polar_bear"),
    LLAMA(103, "minecraft:llama"),
    LLAMA_SPIT(104, "minecraft:llama_spit"),
    PARROT(105, "minecraft:parrot"),
    VILLAGER(120, "minecraft:villager"),
    ENDER_CRYSTAL(200, "minecraft:end_crystal");


    private final int typeID;
    private final String minecraftID;

    MobType(int typeID, String minecraftID) {
        this.typeID = typeID;
        this.minecraftID = minecraftID;
    }

    @SuppressWarnings("Duplicates")
    public static MobType fromTypeID(int typeID) {
        for (MobType mobType : MobType.values()) {
            if (mobType.getTypeID() == typeID) {
                return mobType;
            }
        }

        return null;
    }

    @SuppressWarnings("Duplicates")
    public static MobType fromMinecraftID(String minecraftID) {
        for (MobType mobType : MobType.values()) {
            if (mobType.getMinecraftID().equals(minecraftID)) {
                return mobType;
            }
        }

        return null;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getMinecraftID() {
        return minecraftID;
    }
}
