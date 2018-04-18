package rocks.cleanstone.net.packet.minecraft.enums;

public enum  EntityStatus {
    SPAWN_TIPPED_ARROW_EFFECTS(0);
    //TODO


    private final int statusID;

    EntityStatus(int statusID) {
        this.statusID = statusID;
    }

    public int getStatusID() {
        return statusID;
    }

    @SuppressWarnings("Duplicates")
    public static EntityStatus fromStatusID(int statusID) {
        for (EntityStatus entityStatus : EntityStatus.values()) {
            if (entityStatus.getStatusID() == statusID)
                return entityStatus;
        }

        return null;
    }
}
