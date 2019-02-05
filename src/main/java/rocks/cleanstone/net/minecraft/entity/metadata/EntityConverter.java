package rocks.cleanstone.net.minecraft.entity.metadata;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.VanillaEntity;

/**
 * Converts game entities to {@link VanillaEntity}s
 */
public interface EntityConverter<T extends Entity> {
    VanillaEntity convert(T entity);
}
