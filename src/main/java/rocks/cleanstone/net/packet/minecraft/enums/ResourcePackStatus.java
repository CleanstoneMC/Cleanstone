package rocks.cleanstone.net.packet.minecraft.enums;

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
        switch (statusID) {
            case 0:
                return SUCCESSFULLY_LOADED;
            case 1:
                return DECLINED;
            case 2:
                return FAILED_DOWNLOAD;
            case 3:
                return ACCEPTED;
            default:
                return null;
        }
    }
}
