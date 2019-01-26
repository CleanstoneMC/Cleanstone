package rocks.cleanstone.net.minecraft.entity.metadata;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.entity.Entity;

@Component
public class SimpleEntityConverterRegistry implements EntityConverterRegistry {

    @Override
    public <T extends Entity> void registerConverter(Class<T> entityClass, EntityConverter<T> converter) {
        // TODO
    }

    @Override
    public void unregisterConverter(Class<? extends Entity> entityClass) {
        // TODO
    }

    @Override
    public <T extends Entity> EntityConverter<T> getConverter(Class<T> entityClass) {
        return null;
    }
}
