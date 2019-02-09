package rocks.cleanstone.net.minecraft.entity.metadata.converter;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadata;

/**
 * Extracts basic {@link EntityMetadata} from game entities for use in {@link EntityConverter}s
 */
public interface BaseConverter<T extends Entity> {
    EntityMetadata convert(T entity, ProtocolEntityScheme entityScheme);
}