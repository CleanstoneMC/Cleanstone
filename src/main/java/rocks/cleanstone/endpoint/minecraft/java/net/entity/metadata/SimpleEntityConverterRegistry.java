package rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.converter.EntityConverter;
import rocks.cleanstone.game.entity.Entity;

import java.util.Collection;
import java.util.Set;

@Component
public class SimpleEntityConverterRegistry implements EntityConverterRegistry {

    private final Set<EntityConverter<? extends Entity>> entityConverters = Sets.newConcurrentHashSet();

    @Autowired
    public SimpleEntityConverterRegistry(Collection<EntityConverter> converters) {
        converters.forEach(this::registerConverter);
    }

    @Override
    public void registerConverter(EntityConverter<? extends Entity> converter) {
        entityConverters.add(converter);
    }

    @Override
    public void unregisterConverter(EntityConverter<? extends Entity> converter) {
        entityConverters.remove(converter);
    }

    @Override
    public EntityConverter<? extends Entity> getConverter(Class<? extends Entity> entityClass) {
        return entityConverters.stream()
                .filter(converter -> converter.getEntityClass().isAssignableFrom(entityClass))
                .findAny().orElse(null);
    }
}
