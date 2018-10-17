package rocks.cleanstone.game.inventory;

public enum MainHandSide {
    LEFT(0),
    RIGHT(1);

    private final int handID;

    MainHandSide(int handID) {
        this.handID = handID;
    }

    @SuppressWarnings("Duplicates")
    public static MainHandSide fromHandID(int handID) {
        for (MainHandSide mainHandSide : MainHandSide.values()) {
            if (mainHandSide.getHandID() == handID) {
                return mainHandSide;
            }
        }

        return null;
    }

    public int getHandID() {
        return handID;
    }
}
