package rocks.cleanstone.game.inventory;

public enum VanillaInventoryType implements InventoryType {
    CHEST(0);

    private final int typeID;

    VanillaInventoryType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
