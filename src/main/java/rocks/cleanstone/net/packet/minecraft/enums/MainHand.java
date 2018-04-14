package rocks.cleanstone.net.packet.minecraft.enums;

public enum MainHand {
    LEFT(0),
    RIGHT(1);

    private final int handID;

    MainHand(int handID) {
        this.handID = handID;
    }

    public static MainHand fromHandID(int handID) {
        switch (handID) {
            case 0:
                return LEFT;
            case 1:
                return RIGHT;
            default:
                return null;
        }
    }

    public int getHandID() {
        return handID;
    }
}
