package rocks.cleanstone.net.packet.minecraft.enums;

public enum ChatPosition {
    CHAT(0),
    SYSTEM(1),
    GAME_INFO(2);

    private final int positionID;

    ChatPosition(int positionID) {
        this.positionID = positionID;
    }

    public int getPositionID() {
        return positionID;
    }


    @SuppressWarnings("Duplicates")
    public static ChatPosition fromPositionID(int positionID) {
        for (ChatPosition chatPosition : ChatPosition.values()) {
            if (chatPosition.getPositionID() == positionID) {
                return chatPosition;
            }
        }

        return null;
    }
}
