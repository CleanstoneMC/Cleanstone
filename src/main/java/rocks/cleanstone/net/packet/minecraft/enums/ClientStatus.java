package rocks.cleanstone.net.packet.minecraft.enums;

public enum ClientStatus {
    PERFORM_RESPAWN(0),
    REQUEST_STATS(1);

    private final int statusID;

    ClientStatus(int statusID) {
        this.statusID = statusID;
    }

    @SuppressWarnings("Duplicates")
    public static ClientStatus fromStatusID(int statusID) {
        switch (statusID) {
            case 0:
                return PERFORM_RESPAWN;
            case 1:
                return REQUEST_STATS;
            default:
                return null;
        }
    }

    public int getStatusID() {
        return statusID;
    }
}