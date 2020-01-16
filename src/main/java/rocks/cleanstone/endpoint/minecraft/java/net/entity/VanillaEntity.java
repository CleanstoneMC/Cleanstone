package rocks.cleanstone.endpoint.minecraft.java.net.entity;

import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.EntityMetadata;

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
