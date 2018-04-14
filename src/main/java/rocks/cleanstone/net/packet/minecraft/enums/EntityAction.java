package rocks.cleanstone.net.packet.minecraft.enums;

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
        switch (actionID) {
            case 0:
                return START_SNEAKING;
            case 1:
                return STOP_SNEAKING;
            case 2:
                return LEAVE_BED;
            case 3:
                return START_SPRINTING;
            case 4:
                return STOP_SPRINTING;
            case 5:
                return START_JUMP_BOOST_WITH_HORSE;
            case 6:
                return STOP_JUMP_BOOST_WITH_HORSE;
            case 7:
                return OPEN_HORSE_INVENTORY;
            case 8:
                return START_FLYING_WITH_ELYTRA;
            default:
                return null;
        }
    }

    public int getActionID() {
        return actionID;
    }
}
