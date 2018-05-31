package rocks.cleanstone.net.packet.enums;

public enum BossBarAction {
    ADD(0),
    REMOVE(1),
    UPDATE_HEALTH(2),
    UPDATE_TITLE(3),
    UPDATE_STYLE(4),
    UPDATE_FLAGS(5),;

    private final int actionID;

    BossBarAction(int actionID) {
        this.actionID = actionID;
    }

    @SuppressWarnings("Duplicates")
    public static BossBarAction fromActionID(int actionID) {
        for (BossBarAction bossBarAction : BossBarAction.values()) {
            if (bossBarAction.getActionID() == actionID) {
                return bossBarAction;
            }
        }

        return null;
    }

    public int getActionID() {
        return actionID;
    }
}
