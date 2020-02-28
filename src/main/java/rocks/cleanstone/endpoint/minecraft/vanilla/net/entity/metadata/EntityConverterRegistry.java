package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.metadata.converter.EntityConverter;
import rocks.cleanstone.game.entity.Entity;

/**
 * Registry that links {@link Entity} classes to {@link EntityConverter}s
 */
public interface EntityConverterRegistry {

    void registerConverter(EntityConverter<? extends Entity> converter);

    void unregisterConverter(EntityConverter<? extends Entity> converter);

    EntityConverter<?> getConverter(Class<? extends Entity> entityClass);
}
