package rocks.cleanstone.game.block.state.property;

import java.util.Collection;

/**
 * Registry for all Properties
 */
public interface PropertyRegistry {

    void registerProperty(Property<?> property);

    void unregisterProperty(Property<?> property);

    Collection<Property<?>> getProperties();
}
