package rocks.cleanstone.net.minecraft.entity.metadata;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.metadata.converter.EntityConverter;

/**
 * Registry that links {@link Entity} classes to {@link EntityConverter}s
 */
public interface EntityConverterRegistry {

    <T extends Entity> void registerConverter(Class<T> entityClass, EntityConverter<T> retriever);

    void unregisterConverter(Class<? extends Entity> entityClass);

    <T extends Entity> EntityConverter<T> getConverter(Class<T> entityClass);
}
