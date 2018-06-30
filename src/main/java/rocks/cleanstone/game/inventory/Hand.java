package rocks.cleanstone.game.inventory;

public enum Hand {
    MAIN_HAND(0),
    OFF_HAND(1);

    private final int handID;

    Hand(int handID) {
        this.handID = handID;
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

    public int getHandID() {
        return handID;
    }
}
