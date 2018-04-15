package rocks.cleanstone.net.packet.minecraft.enums;

public enum Hand {
    MAIN_HAND(0),
    OFF_HAND(1);

    private final int handID;

    Hand(int handID) {
        this.handID = handID;
    }

    public int getHandID() {
        return handID;
    }

    @SuppressWarnings("Duplicates")
    public static Hand fromHandID(int handID) {
        for (Hand hand : Hand.values()) {
            if (hand.getHandID() == handID) {
                return hand;
            }
        }

        return null;
    }
}
