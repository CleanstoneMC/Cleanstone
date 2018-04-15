package rocks.cleanstone.net.packet.minecraft.enums;

public enum InteractType {
    INTERACT(0),
    ATTACK(1),
    INTERACT_AT(2);

    private final int typeID;

    InteractType(int typeID) {
        this.typeID = typeID;
    }

    public int getTypeID() {
        return typeID;
    }

    @SuppressWarnings("Duplicates")
    public static InteractType fromTypeID(int typeID) {
        for (InteractType interactType : InteractType.values()) {
            if (interactType.getTypeID() == typeID) {
                return interactType;
            }
        }

        return null;
    }
}
