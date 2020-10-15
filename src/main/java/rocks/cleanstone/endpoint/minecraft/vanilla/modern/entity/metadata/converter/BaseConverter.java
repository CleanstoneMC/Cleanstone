package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.converter;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.EntityMetadata;
import rocks.cleanstone.game.entity.Entity;

/**
 * Extracts basic {@link EntityMetadata} from game entities for use in {@link EntityConverter}s
 */
public interface BaseConverter<T extends Entity> {
    EntityMetadata convert(T entity, ProtocolEntityScheme entityScheme);
}