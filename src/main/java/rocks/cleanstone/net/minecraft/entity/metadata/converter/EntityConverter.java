package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.VanillaEntity;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;

/**
 * Converts game entities to {@link VanillaEntity}s
 */
public interface EntityConverter<T extends Entity> {
    VanillaEntity convert(T entity, ProtocolEntityScheme entityScheme);

    Class<? extends Entity> getEntityClass();
}
