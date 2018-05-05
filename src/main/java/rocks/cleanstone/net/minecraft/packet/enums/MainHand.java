package rocks.cleanstone.net.minecraft.packet.enums;

public enum MainHand {
    LEFT(0),
    RIGHT(1);

    private final int handID;

    MainHand(int handID) {
        this.handID = handID;
    }

    @SuppressWarnings("Duplicates")
    public static MainHand fromHandID(int handID) {
        for (MainHand mainHand : MainHand.values()) {
            if (mainHand.getHandID() == handID) {
                return mainHand;
            }
        }

        return null;
    }

    public int getHandID() {
        return handID;
    }
}
