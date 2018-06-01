package rocks.cleanstone.net.packet.enums;

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
        for (DiggingStatus diggingStatus : DiggingStatus.values()) {
            if (diggingStatus.getStatusID() == statusID) {
                return diggingStatus;
            }
        }

        return null;
    }

    public int getStatusID() {
        return statusID;
    }
}
