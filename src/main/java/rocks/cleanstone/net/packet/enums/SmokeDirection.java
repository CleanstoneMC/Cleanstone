package rocks.cleanstone.net.packet.enums;

public enum SmokeDirection {
    SOUTH_EAST(0),
    SOUTH(1),
    SOUTH_WEST(2),
    EAST(3),
    MIDDLE(4),
    WEST(5),
    NORTH_EAST(6),
    NORTH(7),
    NORTH_WEST(8);

    private final int directionID;

    SmokeDirection(int directionID) {
        this.directionID = directionID;
    }

    @SuppressWarnings("Duplicates")
    public static SmokeDirection fromDirectionID(int directionID) {
        for (SmokeDirection direction : SmokeDirection.values()) {
            if (direction.getDirectionID() == directionID) {
                return direction;
            }
        }

        return null;
    }

    public int getDirectionID() {
        return directionID;
    }
}
