package rocks.cleanstone.game.block;

import rocks.cleanstone.utils.Vector;

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

    public Face getOpposite() {

        String newOffset;
        if (offset.charAt(0) == '-') {
            newOffset = offset.replace('-', '+');
        } else {
            newOffset = offset.replace('+', '-');
        }

        return Face.fromOffset(newOffset);
    }

    public int getFaceID() {
        return faceID;
    }

    public String getOffset() {
        return offset;
    }

    public Vector toUnitVector() {
        Vector vector = new Vector();
        switch (this) {
            case TOP:
                vector.addY(1);
                break;
            case BOTTOM:
                vector.addY(-1);
                break;
            case NORTH:
                vector.addZ(-1);
                break;
            case SOUTH:
                vector.addZ(1);
                break;
            case EAST:
                vector.addX(1);
                break;
            case WEST:
                vector.addX(-1);
                break;
        }
        return vector;
    }
}
