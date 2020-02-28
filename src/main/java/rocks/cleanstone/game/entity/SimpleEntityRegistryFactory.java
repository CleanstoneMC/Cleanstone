package rocks.cleanstone.game.entity;

import org.springframework.stereotype.Component;

@Component
public class SimpleEntityRegistryFactory implements EntityRegistryFactory {
    @Override
    public EntityRegistry get() {
        return new SimpleEntityRegistry();
    }
}
