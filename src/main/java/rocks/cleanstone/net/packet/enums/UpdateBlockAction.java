package rocks.cleanstone.net.packet.enums;

public enum UpdateBlockAction {
    MOB_SPAWNER_DATA(1),
    COMMAND_BLOCK_TEXT(2),
    BEACON_POWERS(3),
    MOB_HEAD_ROTATION_AND_SKIN(4),
    FLOWER_POT_FLOWER_TYPE(5),
    BANNER_COLOR_AND_PATTERNS(6),
    STRUCTURE_TILE_ENTITY_DATA(7),
    END_GATEWAY_DESTINATION(8),
    SIGN_TEXT(9),
    SHULKER_BOX(10),
    BED_COLOR(11);

    private final int actionID;

    UpdateBlockAction(int actionID) {
        this.actionID = actionID;
    }

    @SuppressWarnings("Duplicates")
    public static UpdateBlockAction fromActionID(int actionID) {
        for (UpdateBlockAction updateBlockAction : UpdateBlockAction.values()) {
            if (updateBlockAction.getActionID() == actionID) {
                return updateBlockAction;
            }
        }

        return null;
    }

    public int getActionID() {
        return actionID;
    }
}
