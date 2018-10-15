package rocks.cleanstone.net.minecraft.packet.enums;

public enum ResourcePackStatus {
    SUCCESSFULLY_LOADED(0),
    DECLINED(1),
    FAILED_DOWNLOAD(2),
    ACCEPTED(3);

    private final int status;

    ResourcePackStatus(int statusID) {
        this.status = statusID;
    }

    @SuppressWarnings("Duplicates")
    public static ResourcePackStatus fromStatusID(int statusID) {
        for (final ResourcePackStatus resourcePackStatus : ResourcePackStatus.values()) {
            if (resourcePackStatus.getStatus() == statusID) {
                return resourcePackStatus;
            }
        }

        return null;
    }

    public int getStatus() {
        return status;
    }
}
