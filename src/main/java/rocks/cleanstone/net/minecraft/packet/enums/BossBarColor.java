package rocks.cleanstone.net.minecraft.packet.enums;

public enum BossBarColor {
    PINK(0),
    BLUE(1),
    RED(2),
    GREEN(3),
    YELLOW(4),
    PURPLE(5),
    WHITE(6);

    private final int colorID;

    BossBarColor(int colorID) {
        this.colorID = colorID;
    }

    @SuppressWarnings("Duplicates")
    public static BossBarColor fromColorID(int colorID) {
        for (final BossBarColor bossBarColor : BossBarColor.values()) {
            if (bossBarColor.getColorID() == colorID) {
                return bossBarColor;
            }
        }

        return null;
    }

    public int getColorID() {
        return colorID;
    }
}
