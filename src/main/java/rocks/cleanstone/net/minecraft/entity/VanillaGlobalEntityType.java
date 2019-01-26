package rocks.cleanstone.net.minecraft.entity;

public enum VanillaGlobalEntityType {
    THUNDERBOLT(1);

    private final int entityID;

    VanillaGlobalEntityType(int entityID) {
        this.entityID = entityID;
    }

    @SuppressWarnings("Duplicates")
    public static VanillaGlobalEntityType fromTypeID(int typeID) {
        for (VanillaGlobalEntityType globalEntityType : VanillaGlobalEntityType.values()) {
            if (globalEntityType.getEntityID() == typeID) {
                return globalEntityType;
            }
        }

        return null;
    }

    public int getEntityID() {
        return entityID;
    }
}
