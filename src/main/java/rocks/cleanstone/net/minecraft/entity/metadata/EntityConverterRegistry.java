package rocks.cleanstone.net.minecraft.entity.metadata;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.metadata.converter.EntityConverter;

/**
 * Registry that links {@link Entity} classes to {@link EntityConverter}s
 */
public interface EntityConverterRegistry {

    void registerConverter(EntityConverter<? extends Entity> converter);

    void unregisterConverter(EntityConverter<? extends Entity> converter);

    EntityConverter<?> getConverter(Class<? extends Entity> entityClass);
}
