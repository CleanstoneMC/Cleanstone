package rocks.cleanstone.net.minecraft.entity;

import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadata;

/**
 * A captured state of a "mob" entity as known by the minecraft client
 */
public class VanillaEntity {

    private final int entityTypeID;
    private final EntityMetadata entityMetadata;

    public VanillaEntity(int entityTypeID, EntityMetadata entityMetadata) {
        this.entityTypeID = entityTypeID;
        this.entityMetadata = entityMetadata;
    }

    public int getEntityTypeID() {
        return entityTypeID;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }
}
