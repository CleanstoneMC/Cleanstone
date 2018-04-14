package rocks.cleanstone.net.packet.minecraft.enums;

public enum AdvancementTabStatus {
    OPENED(0),
    CLOSED(1);

    private final int statusID;

    AdvancementTabStatus(int statusID) {
        this.statusID = statusID;
    }

    @SuppressWarnings("Duplicates")
    public static AdvancementTabStatus fromStatusID(int statusID) {
        switch (statusID) {
            case 0:
                return OPENED;
            case 1:
                return CLOSED;
            default:
                return null;
        }
    }

    public int getStatusID() {
        return statusID;
    }
}
