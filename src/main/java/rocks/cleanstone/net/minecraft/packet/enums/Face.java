package rocks.cleanstone.net.minecraft.packet.enums;

public enum Face {
    BOTTOM(0, "-Y"),
    TOP(1, "+Y"),
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
        for (Face face : Face.values()) {
            if (face.getFaceID() == faceID) {
                return face;
            }
        }

        return null;
    }

    @SuppressWarnings("Duplicates")
    public static Face fromOffset(String offset) {
        for (Face face : Face.values()) {
            if (face.getOffset().equals(offset)) {
                return face;
            }
        }

        return null;
    }

    public int getFaceID() {
        return faceID;
    }

    public String getOffset() {
        return offset;
    }
}
