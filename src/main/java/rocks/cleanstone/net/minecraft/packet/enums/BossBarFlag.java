package rocks.cleanstone.net.minecraft.packet.enums;

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
            if ((bitmask & (1 << bossBarFlag.getBit())) == 1) {
                bossBarFlagList.add(bossBarFlag);
            }
        }

        return bossBarFlagList.toArray(new BossBarFlag[bossBarFlagList.size()]);
    }

    public int getBit() {
        return bit;
    }
}
