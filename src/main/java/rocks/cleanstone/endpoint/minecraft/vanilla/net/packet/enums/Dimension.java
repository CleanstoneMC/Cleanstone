package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums;

public enum Dimension {
    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private final int dimensionID;

    Dimension(int dimensionID) {
        this.dimensionID = dimensionID;
    }

    public static Dimension fromDimensionID(int dimensionID) {
        for (Dimension dimension : Dimension.values()) {
            if (dimension.getDimensionID() == dimensionID) {
                return dimension;
            }
        }

        return null;
    }

    public int getDimensionID() {
        return dimensionID;
    }
}
