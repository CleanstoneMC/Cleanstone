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
        char axis = offset.charAt(1);
        if (offset.charAt(0) == '-') {
            return Face.fromOffset("+" + axis);
        } else {
            return Face.fromOffset("-" + axis);
        }
    }

    public int getFaceID() {
        return faceID;
    }

    public String getOffset() {
        return offset;
    }

    public Vector toUnitVector() {
        switch (this) {
            case BOTTOM:
                return new Vector(0, -1, 0);
            case TOP:
                return new Vector(0, 1, 0);
            case NORTH:
                return new Vector(0, 0, -1);
            case SOUTH:
                return new Vector(0, 0, 1);
            case WEST:
                return new Vector(-1, 0, 0);
            case EAST:
                return new Vector(1, 0, 0);
            default:
                throw new IllegalStateException("can't map face " + this + " to unit vector");
        }
    }
}
