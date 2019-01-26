package rocks.cleanstone.net.minecraft.entity.metadata;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.VanillaEntity;

public interface EntityConverter<T extends Entity> {
    VanillaEntity convert(T entity);
}
