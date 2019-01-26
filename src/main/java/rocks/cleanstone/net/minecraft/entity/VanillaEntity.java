package rocks.cleanstone.net.minecraft.entity;

import rocks.cleanstone.net.minecraft.packet.data.EntityMetadata;

public class VanillaEntity {

    private final VanillaEntityType entityType;
    private final EntityMetadata entityMetadata;

    public VanillaEntity(VanillaEntityType entityType, EntityMetadata entityMetadata) {
        this.entityType = entityType;
        this.entityMetadata = entityMetadata;
    }

    public VanillaEntityType getEntityType() {
        return entityType;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }
}
