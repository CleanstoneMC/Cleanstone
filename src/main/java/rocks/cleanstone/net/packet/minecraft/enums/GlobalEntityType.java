package rocks.cleanstone.net.packet.minecraft.enums;

public enum GlobalEntityType {
    THUNDERBOLT(1);

    private final int entityID;

    GlobalEntityType(int entityID) {
        this.entityID = entityID;
    }

    public int getEntityID() {
        return entityID;
    }

    @SuppressWarnings("Duplicates")
    public static GlobalEntityType fromTypeID(int typeID) {
        for (GlobalEntityType globalEntityType : GlobalEntityType.values()) {
            if (globalEntityType.getEntityID() == typeID) {
                return globalEntityType;
            }
        }

        return null;
    }
}
