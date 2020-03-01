package rocks.cleanstone.game.block.state.property;

import com.google.common.collect.Sets;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
public class SimplePropertyRegistry implements PropertyRegistry {

    private final Set<Property<?>> properties = Sets.newConcurrentHashSet();

    public SimplePropertyRegistry() {
    }

    @Override
    public void registerProperty(Property<?> property) {
        properties.add(property);
    }

    @Override
    public void unregisterProperty(Property<?> property) {
        properties.remove(property);
    }

    @Override
    public Collection<Property<?>> getProperties() {
        return Collections.unmodifiableSet(properties);
    }
}
