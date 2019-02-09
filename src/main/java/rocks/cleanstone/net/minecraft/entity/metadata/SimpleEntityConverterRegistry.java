package rocks.cleanstone.net.minecraft.entity.metadata;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.metadata.converter.EntityConverter;

@Component
public class SimpleEntityConverterRegistry implements EntityConverterRegistry {

    private final Map<Class<?>, EntityConverter<?>> entityClassConverterMap = new ConcurrentHashMap<>();

    public SimpleEntityConverterRegistry(Collection<EntityConverter> converters) {
        //noinspection unchecked
        converters.forEach(converter -> registerConverter(converter.getEntityClass(), converter));
    }

    @Override
    public <T extends Entity> void registerConverter(Class<T> entityClass, EntityConverter<T> converter) {
        entityClassConverterMap.put(entityClass, converter);
    }

    @Override
    public void unregisterConverter(Class<? extends Entity> entityClass) {
        entityClassConverterMap.remove(entityClass);
    }

    @Override
    public <T extends Entity> EntityConverter<T> getConverter(Class<T> entityClass) {
        //noinspection unchecked
        return (EntityConverter<T>) entityClassConverterMap.get(entityClass);
    }
}
