package rocks.cleanstone.net.minecraft.packet.enums;

public enum Dimension {
    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private final int dimesionID;

    Dimension(int dimesionID) {
        this.dimesionID = dimesionID;
    }

    @SuppressWarnings("Duplicates")
    public static Dimension fromDimensionID(int dimesionID) {
        for (Dimension dimension : Dimension.values()) {
            if (dimension.getDimesionID() == dimesionID) {
                return dimension;
            }
        }

        return null;
    }

    public int getDimesionID() {
        return dimesionID;
    }
}
