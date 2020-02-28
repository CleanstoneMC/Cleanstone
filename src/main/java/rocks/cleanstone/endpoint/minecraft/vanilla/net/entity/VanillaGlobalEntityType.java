package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity;

public enum VanillaGlobalEntityType {
    THUNDERBOLT(1);

    private final int entityID;

    VanillaGlobalEntityType(int entityID) {
        this.entityID = entityID;
    }

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
