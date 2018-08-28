package rocks.cleanstone.player.event;

public enum StandardMoveReason implements MoveReason {
    /**
     * Client sent move packet
     */
    CLIENT_ACTION(0),
    /**
     * Client triggered actions (e.g. commands, enderpearls, entering minigame arena)
     */
    CLIENT_REQUEST(1),
    /**
     * Player moved by server event (e.g. respawn, teleporting out of protected area)
     */
    SERVER_EVENT(2),
    /**
     * Player moved by Cleanstone (e.g. physics, postponed movement corrections)
     */
    SERVER_INTERNAL(3);

    private final int typeID;

    StandardMoveReason(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
