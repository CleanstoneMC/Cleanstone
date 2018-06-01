package rocks.cleanstone.net.packet.enums;

public enum Particle {
    EXPLODE(0),
    LARGE_EXPLODE(1),
    HUGE_EXPLOSION(2),
    FIREWORKS_SPARK(3),
    BUBBLE(4),
    SPLASH(5),
    WAKE(6),
    SUSPENDED(7),
    DEPTH_SUSPEND(8),
    CRIT(9),
    MAGIC_CRIT(10),
    SMOKE(11),
    LARGE_SMOKE(12),
    SPELL(13),
    INSTANT_SPELL(14),
    MOB_SPELL(15),
    MOB_SPELL_AMBIENT(16),
    WITCH_MAGIC(17),
    DRIP_WATER(18),
    DRIP_LAVA(19),
    ANGRY_VILLAGER(20),
    HAPPY_VILLAGER(21),
    TOWN_AURA(22),
    NOTE(23),
    PORTAL(24),
    ENCHANTMENT_TABLE(25),
    FLAME(26),
    LAVA(27),
    FOOTSTEP(28),
    CLOUD(29),
    RED_DUST(30),
    SNOWBALL_POOF(31),
    SNOW_SHOVEL(32),
    SLIME(33),
    HEART(34),
    BARRIER(35),
    ICON_CRACK(36),
    BLOCK_CRACK(37),
    BLOCK_DUST(38),
    DROPLET(39),
    TAKE(40),
    MOB_APPEARANCE(41),
    DRAGON_BREATH(42),
    END_ROD(43),
    DAMAGE_INDICATOR(44),
    SWEEP_ATTACK(45),
    FALLING_DUST(46),
    TOTEM(47),
    SPIT(48);

    private final int particleID;

    Particle(int particleID) {
        this.particleID = particleID;
    }

    @SuppressWarnings("Duplicates")
    public static Particle fromParticleID(int particleID) {
        for (Particle particle : Particle.values()) {
            if (particle.getParticleID() == particleID) {
                return particle;
            }
        }

        return null;
    }

    public int getParticleID() {
        return particleID;
    }
}
