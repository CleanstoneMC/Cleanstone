package rocks.cleanstone.game.entity;

/**
 * Enum implementations map an entity class to a unique hardcoded typeID for the {@link EntitySerialization}
 */
public interface EntityType {
    int getTypeID();

    Class<? extends Entity> getEntityClass();
}
