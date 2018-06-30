package rocks.cleanstone.net.packet.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlayerAbility {
    IS_INVULNERABLE(0x01),
    IS_FLYING(0x02),
    CAN_FLY(0x04),
    IS_CREATIVE(0x08);

    private final int bit;

    PlayerAbility(int bit) {
        this.bit = bit;
    }

    public static PlayerAbility[] fromBitMask(int bitmask) {
        List<PlayerAbility> playerAbilities = new ArrayList<>();

        for (PlayerAbility playerAbility : PlayerAbility.values()) {
            int bit = playerAbility.getBit();
            if ((bitmask & bit) == bit) {
                playerAbilities.add(playerAbility);
            }
        }

        return playerAbilities.toArray(new PlayerAbility[0]);
    }

    public static int toBitMask(PlayerAbility... abilities) {
        int bitmask = 0;
        for (PlayerAbility playerAbility : abilities) {
            int bit = playerAbility.getBit();
            bitmask |= bit;
        }

        return bitmask;
    }

    public int getBit() {
        return bit;
    }
}
