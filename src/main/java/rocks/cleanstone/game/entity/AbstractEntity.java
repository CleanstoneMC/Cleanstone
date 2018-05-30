package rocks.cleanstone.game.entity;

public abstract class AbstractEntity implements Entity {

    private int entityID;

    private final EntityType type;

    private Location location;

    protected AbstractEntity(int entityID, EntityType type, Location location) {
        this.entityID = entityID;
        this.type = type;
        this.location = location;
    }

    protected AbstractEntity(EntityType type, Location location) {
        this.type = type;
        this.location = location;
    }

    public EntityType getType() {
        return type;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
