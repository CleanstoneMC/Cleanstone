package rocks.cleanstone.game.entity.vanilla;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.EntityType;
import rocks.cleanstone.game.entity.vanilla.mob.SimpleChicken;

public enum VanillaEntityType implements EntityType {
    HUMAN(0, SimpleHuman.class),
    CHICKEN(1, SimpleChicken.class);

    private final int typeID;
    private final Class<? extends Entity> entityClass;

    VanillaEntityType(int typeID, Class<? extends Entity> entityClass) {
        this.typeID = typeID;
        this.entityClass = entityClass;
    }

    @Override
    public int getTypeID() {
        return typeID;
    }

    @Override
    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}
