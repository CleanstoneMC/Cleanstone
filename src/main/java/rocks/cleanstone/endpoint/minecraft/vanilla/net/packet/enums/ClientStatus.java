package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums;

public enum ClientStatus {
    PERFORM_RESPAWN(0),
    REQUEST_STATS(1);

    private final int statusID;

    ClientStatus(int statusID) {
        this.statusID = statusID;
    }

    public static ClientStatus fromStatusID(int statusID) {
        for (ClientStatus clientStatus : ClientStatus.values()) {
            if (clientStatus.getStatusID() == statusID) {
                return clientStatus;
            }
        }

        return null;
    }

    public int getStatusID() {
        return statusID;
    }
}