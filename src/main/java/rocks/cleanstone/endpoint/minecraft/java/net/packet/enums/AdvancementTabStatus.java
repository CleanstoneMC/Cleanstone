package rocks.cleanstone.endpoint.minecraft.java.net.packet.enums;

public enum AdvancementTabStatus {
    OPENED(0),
    CLOSED(1);

    private final int statusID;

    AdvancementTabStatus(int statusID) {
        this.statusID = statusID;
    }

    public static AdvancementTabStatus fromStatusID(int statusID) {
        for (AdvancementTabStatus advancementTabStatus : AdvancementTabStatus.values()) {
            if (advancementTabStatus.getStatusID() == statusID)
                return advancementTabStatus;
        }

        return null;
    }

    public int getStatusID() {
        return statusID;
    }
}
