package rocks.cleanstone.net.minecraft.packet.enums;

import java.util.ArrayList;
import java.util.List;

public enum DisplayedSkinParts {
    CAPE(0x01),
    JACKET(0x02),
    LEFT_SLEEVE(0x04),
    RIGHT_SLEEVE(0x08),
    LEFT_PANTS_LEG(0x10),
    RIGHT_PANTS_LEG(0x20),
    HAT(0x40),
    UNUSED(0x80);

    private final int bit;

    DisplayedSkinParts(int bit) {
        this.bit = bit;
    }

    public static DisplayedSkinParts[] fromBitMask(int bitmask) {
        List<DisplayedSkinParts> displayedSkinPartsList = new ArrayList<>();

        for (DisplayedSkinParts displayedSkinPart : DisplayedSkinParts.values()) {
            int bit = displayedSkinPart.getBit();
            if ((bitmask & bit) == bit) {
                displayedSkinPartsList.add(displayedSkinPart);
            }
        }

        return displayedSkinPartsList.toArray(new DisplayedSkinParts[0]);
    }

    public int getBit() {
        return bit;
    }
}
