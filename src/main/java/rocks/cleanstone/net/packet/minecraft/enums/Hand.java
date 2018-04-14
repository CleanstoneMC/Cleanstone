package rocks.cleanstone.net.packet.minecraft.enums;

public enum Hand {
    MAIN_HAND(0),
    OFF_HAND(1);

    private final int handID;

    Hand(int handID) {
        this.handID = handID;
    }

    @SuppressWarnings("Duplicates")
    public static Hand fromHandID(int handID) {
        switch (handID) {
            case 0:
                return MAIN_HAND;
            case 1:
                return OFF_HAND;
            default:
                return null;
        }
    }

    public int getHandID() {
        return handID;
    }
}
