package rocks.cleanstone.game.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.metadata.MetadataEntityCodec;
import rocks.cleanstone.game.entity.vanilla.VanillaEntityType;

@Component
public class SimpleEntityTypeRegistry implements EntityTypeRegistry {

    private final Map<EntityType, InOutCodec<Entity, ByteBuf>> entityTypeCodecMap = Maps.newConcurrentMap();

    public SimpleEntityTypeRegistry() {
        Arrays.stream(VanillaEntityType.values())
                .forEach(entityType -> registerEntityType(entityType, new MetadataEntityCodec()));
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
