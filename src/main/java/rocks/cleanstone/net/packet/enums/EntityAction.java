package rocks.cleanstone.net.packet.enums;

public enum EntityAction {
    START_SNEAKING(0),
    STOP_SNEAKING(1),
    LEAVE_BED(2),
    START_SPRINTING(3),
    STOP_SPRINTING(4),
    START_JUMP_BOOST_WITH_HORSE(5),
    STOP_JUMP_BOOST_WITH_HORSE(6),
    OPEN_HORSE_INVENTORY(7),
    START_FLYING_WITH_ELYTRA(8),;

    private final int actionID;

    EntityAction(int actionID) {
        this.actionID = actionID;
    }

    @SuppressWarnings("Duplicates")
    public static EntityAction fromActionID(int actionID) {
        for (EntityAction entityAction : EntityAction.values()) {
            if (entityAction.getActionID() == actionID) {
                return entityAction;
            }
        }

        return null;
    }

    public int getActionID() {
        return actionID;
    }
}
