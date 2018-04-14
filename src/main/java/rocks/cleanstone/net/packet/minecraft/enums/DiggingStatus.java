package rocks.cleanstone.net.packet.minecraft.enums;

public enum DiggingStatus {
    STARTED_DIGGING(0),
    CANCELLED_DIGGING(1),
    FINISHED_DIGGING(2),
    DROP_ITEM_STACK(3),
    DROP_ITEM(4),
    UPDATE_ITEM_STATE(5),
    SWAP_ITEM_IN_HAND(6);

    private final int statusID;

    DiggingStatus(int statusID) {
        this.statusID = statusID;
    }

    @SuppressWarnings("Duplicates")
    public static DiggingStatus fromStatusID(int statusID) {
        switch (statusID) {
            case 0:
                return STARTED_DIGGING;
            case 1:
                return CANCELLED_DIGGING;
            case 2:
                return FINISHED_DIGGING;
            case 3:
                return DROP_ITEM_STACK;
            case 4:
                return DROP_ITEM;
            case 5:
                return UPDATE_ITEM_STATE;
            case 6:
                return SWAP_ITEM_IN_HAND;
            default:
                return null;
        }
    }

    public int getStatusID() {
        return statusID;
    }
}
