package rocks.cleanstone.net.minecraft.entity;

import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadata;

/**
 * A captured state of a "mob" entity as known by the minecraft client
 */
public class VanillaEntity {

    private final VanillaMobType entityType;
    private final EntityMetadata entityMetadata;

    public VanillaEntity(VanillaMobType entityType, EntityMetadata entityMetadata) {
        this.entityType = entityType;
        this.entityMetadata = entityMetadata;
    }

    public VanillaMobType getEntityType() {
        return entityType;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }
}
