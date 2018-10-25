package rocks.cleanstone.game.entity;

import lombok.Data;
import rocks.cleanstone.game.world.World;

@Data
public abstract class AbstractEntity implements Entity {
    private final EntityType type;
    private final World world;
    private final int entityID;
    private boolean persistent;
    protected RotatablePosition position;

    protected AbstractEntity(EntityType type, World world, RotatablePosition position, boolean persistent) {
        this.type = type;
        this.world = world;
        this.position = position;
        this.persistent = persistent;

        entityID = world.getEntityRegistry().acquireEntityID();
    }
}
