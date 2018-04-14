package rocks.cleanstone.net.packet.minecraft.enums;

public enum Face {
    BOTTOM(0, "-Y"),
    TOP(1,"+Y"),
    NORTH(2, "-Z"),
    SOUTH(3, "+Z"),
    WEST(4, "-X"),
    EAST(5, "+X");

    private final int faceID;
    private final String offset;

    Face(int faceID, String offset) {
        this.faceID = faceID;
        this.offset = offset;
    }

    @SuppressWarnings("Duplicates")
    public static Face fromFaceID(int faceID) {
        switch (faceID) {
            case 0:
                return BOTTOM;
            case 1:
                return TOP;
            case 2:
                return NORTH;
            case 3:
                return SOUTH;
            case 4:
                return WEST;
            case 5:
                return EAST;
            default:
                return null;
        }
    }

    public int getFaceID() {
        return faceID;
    }

    public String getOffset() {
        return offset;
    }
}
