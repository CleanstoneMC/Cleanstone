package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.converter;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.VanillaEntity;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.game.entity.Entity;

/**
 * Converts game entities to {@link VanillaEntity}s
 */
public interface EntityConverter<T extends Entity> {
    VanillaEntity convert(T entity, ProtocolEntityScheme entityScheme);

    Class<? extends Entity> getEntityClass();
}
