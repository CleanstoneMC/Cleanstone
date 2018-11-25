package rocks.cleanstone.game.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.InOutCodec;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

@Component
public class SimpleEntityTypeRegistry implements EntityTypeRegistry {

    private final Map<EntityType, InOutCodec<Entity, ByteBuf>> entityTypeCodecMap = Maps.newConcurrentMap();

    public SimpleEntityTypeRegistry() {
        // TODO register vanilla entities
    }

    @Override
    public void registerEntityType(EntityType entityType, InOutCodec<Entity, ByteBuf> codec) {
        entityTypeCodecMap.put(entityType, codec);
    }

    @Override
    public void unregisterEntityType(EntityType entityType) {
        entityTypeCodecMap.remove(entityType);
    }

    @Override
    public Collection<EntityType> getAllEntityTypes() {
        return ImmutableSet.copyOf(entityTypeCodecMap.keySet());
    }

    @Nullable
    @Override
    public InOutCodec<Entity, ByteBuf> getEntityCodec(EntityType entityType) {
        return entityTypeCodecMap.get(entityType);
    }
}
