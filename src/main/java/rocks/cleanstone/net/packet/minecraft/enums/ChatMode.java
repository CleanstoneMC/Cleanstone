package rocks.cleanstone.net.packet.minecraft.enums;

public enum ChatMode {
    ENABLED(0),
    COMMANDS_ONLY(1),
    HIDDEN(2);

    private final int modeID;

    ChatMode(int modeID) {
        this.modeID = modeID;
    }

    public static ChatMode fromModeID(int modeID) {
        switch (modeID) {
            case 0:
                return ENABLED;
            case 1:
                return COMMANDS_ONLY;
            case 2:
                return HIDDEN;
            default:
                return null;
        }
    }

    public int getModeID() {
        return modeID;
    }
}
