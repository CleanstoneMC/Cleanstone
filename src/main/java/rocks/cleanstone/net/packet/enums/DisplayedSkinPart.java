package rocks.cleanstone.net.packet.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum DisplayedSkinPart {
    CAPE(0x01),
    JACKET(0x02),
    LEFT_SLEEVE(0x04),
    RIGHT_SLEEVE(0x08),
    LEFT_PANTS_LEG(0x10),
    RIGHT_PANTS_LEG(0x20),
    HAT(0x40),
    UNUSED(0x80);

    private final int bit;

    DisplayedSkinPart(int bit) {
        this.bit = bit;
    }

    public static Collection<DisplayedSkinPart> fromBitMask(int bitmask) {
        List<DisplayedSkinPart> displayedSkinPartList = new ArrayList<>();

        for (DisplayedSkinPart displayedSkinPart : DisplayedSkinPart.values()) {
            int bit = displayedSkinPart.getBit();
            if ((bitmask & bit) == bit) {
                displayedSkinPartList.add(displayedSkinPart);
            }
        }

        return displayedSkinPartList;
    }

    public int getBit() {
        return bit;
    }
}
