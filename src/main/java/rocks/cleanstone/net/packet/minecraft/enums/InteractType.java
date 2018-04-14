package rocks.cleanstone.net.packet.minecraft.enums;

public enum InteractType {
    INTERACT(0),
    ATTACK(1),
    INTERACT_AT(2);

    private final int typeID;

    InteractType(int typeID) {
        this.typeID = typeID;
    }

    @SuppressWarnings("Duplicates")
    public static InteractType fromTypeID(int typeID) {
        switch (typeID) {
            case 0:
                return INTERACT;
            case 1:
                return ATTACK;
            case 2:
                return INTERACT_AT;
            default:
                return null;
        }
    }

    public int getTypeID() {
        return typeID;
    }
}
