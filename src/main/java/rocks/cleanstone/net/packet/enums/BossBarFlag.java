package rocks.cleanstone.net.packet.enums;

import java.util.ArrayList;
import java.util.List;

public enum BossBarFlag {
    DARKEN_SKY(0x1),
    DRAGON_BAR(0x2);

    private final int bit;

    BossBarFlag(int bit) {
        this.bit = bit;
    }

    public static BossBarFlag[] fromBitMask(int bitmask) {
        List<BossBarFlag> bossBarFlagList = new ArrayList<>();

        for (BossBarFlag bossBarFlag : BossBarFlag.values()) {
            int bit = bossBarFlag.getBit();
            if ((bitmask & bit) == bit) {
                bossBarFlagList.add(bossBarFlag);
            }
        }

        return bossBarFlagList.toArray(new BossBarFlag[0]);
    }

    public int getBit() {
        return bit;
    }
}
