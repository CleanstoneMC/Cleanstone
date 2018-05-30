package rocks.cleanstone.game.entity;

public interface Entity {

    EntityType getType();

    int getEntityID();

    Location getLocation();

    void setLocation(Location location);
}
