package rocks.cleanstone.game.chat;

public enum ChatMode {
    ENABLED(0),
    COMMANDS_ONLY(1),
    HIDDEN(2);

    private final int modeID;

    ChatMode(int modeID) {
        this.modeID = modeID;
    }

    @SuppressWarnings("Duplicates")
    public static ChatMode fromModeID(int modeID) {
        for (ChatMode chatMode : ChatMode.values()) {
            if (chatMode.getModeID() == modeID) {
                return chatMode;
            }
        }

        return null;
    }

    public int getModeID() {
        return modeID;
    }
}
