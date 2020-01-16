package rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.converter;

import rocks.cleanstone.endpoint.minecraft.java.net.entity.VanillaEntity;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.game.entity.Entity;

/**
 * Converts game entities to {@link VanillaEntity}s
 */
public interface EntityConverter<T extends Entity> {
    VanillaEntity convert(T entity, ProtocolEntityScheme entityScheme);

    Class<? extends Entity> getEntityClass();
}
