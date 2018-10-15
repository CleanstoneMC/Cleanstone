package rocks.cleanstone.net.minecraft.packet.enums;

public enum EntityStatus {
    SPAWN_TIPPED_ARROW_EFFECTS(0);
    //TODO


    private final int statusID;

    EntityStatus(int statusID) {
        this.statusID = statusID;
    }

    @SuppressWarnings("Duplicates")
    public static EntityStatus fromStatusID(int statusID) {
        for (final EntityStatus entityStatus : EntityStatus.values()) {
            if (entityStatus.getStatusID() == statusID)
                return entityStatus;
        }

        return null;
    }

    public int getStatusID() {
        return statusID;
    }
}
