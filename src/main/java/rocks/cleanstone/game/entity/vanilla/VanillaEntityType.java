package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.EntityType;

public enum VanillaEntityType implements EntityType {
    HUMAN(0);

    private final int typeID;

    VanillaEntityType(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }
}
