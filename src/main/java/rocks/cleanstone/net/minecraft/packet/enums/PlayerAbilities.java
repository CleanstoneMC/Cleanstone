package rocks.cleanstone.net.minecraft.packet.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlayerAbilities {
    IS_CREATIVE(0x01),
    IS_FLYING(0x02),
    CAN_FLY(0x04),
    DAMAGE_DISABLED(0x08);

    private final int bit;

    PlayerAbilities(int bit) {
        this.bit = bit;
    }

    public static PlayerAbilities[] fromBitMask(int bitmask) {
        List<PlayerAbilities> playerAbilitiesList = new ArrayList<>();

        for (PlayerAbilities playerAbilities : PlayerAbilities.values()) {
            int bit = playerAbilities.getBit();
            if ((bitmask & bit) == bit) {
                playerAbilitiesList.add(playerAbilities);
            }
        }

        return playerAbilitiesList.toArray(new PlayerAbilities[0]);
    }

    public static int toBitMask(PlayerAbilities... abilities) {
        int bitmask = 0;
        for (PlayerAbilities playerAbilities : abilities) {
            int bit = playerAbilities.getBit();
            bitmask |= bit;
        }

        return bitmask;
    }

    public int getBit() {
        return bit;
    }
}
