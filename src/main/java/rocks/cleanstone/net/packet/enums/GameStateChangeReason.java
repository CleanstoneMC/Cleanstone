package rocks.cleanstone.net.packet.enums;

public enum GameStateChangeReason {
    INVALID_BED(0),
    END_RAINING(1),
    BEGIN_RAINING(2),
    CHANGE_GAMEMODE(3),
    EXIT_END(4),
    DEMO_MESSAGE(5),
    ARROW_HITTING_PLAYER(6),
    FADE_VALUE(7),
    FADE_TIME(8),
    PLAY_ELDER_GUARDIAN_APPEARANCE(10);

    private final int reasonID;

    GameStateChangeReason(int reasonID) {
        this.reasonID = reasonID;
    }

    @SuppressWarnings("Duplicates")
    public static GameStateChangeReason fromReasonID(int reasonID) {
        for (GameStateChangeReason gameStateChangeReason : GameStateChangeReason.values()) {
            if (gameStateChangeReason.getReasonID() == reasonID)
                return gameStateChangeReason;
        }

        return null;
    }

    public int getReasonID() {
        return reasonID;
    }
}
