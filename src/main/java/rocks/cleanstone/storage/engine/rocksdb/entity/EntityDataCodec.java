package rocks.cleanstone.storage.engine.rocksdb.entity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class EntityDataCodec implements InOutCodec<EntityData, ByteBuf> {

    private final EntitySerialization entitySerialization;

    public EntityDataCodec(EntitySerialization entitySerialization) {
        this.entitySerialization = entitySerialization;
    }

    @Override
    public EntityData decode(ByteBuf data) throws IOException {
        Set<Entity> entities = new HashSet<>();
        int entityAmount = ByteBufUtils.readVarInt(data);
        while (entityAmount-- != 0) {
            entities.add(entitySerialization.deserializeEntity(data));
        }
        return new EntityData(entities);
    }

    @Override
    public ByteBuf encode(EntityData entityData) throws IOException {
        ByteBuf data = Unpooled.buffer();
        Collection<Entity> entities = entityData.getEntities().stream()
                .filter(Entity::isPersistent).collect(Collectors.toSet());
        ByteBufUtils.writeVarInt(data, entities.size());
        for (Entity entity : entities) {
            ByteBuf serializedEntity = entitySerialization.serializeEntity(entity);
            data.writeBytes(serializedEntity);
            serializedEntity.release();
        }
        return data;
    }
}
