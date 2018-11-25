package rocks.cleanstone.game.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SimpleEntitySerialization implements EntitySerialization {

    private final EntityTypeRegistry entityTypeRegistry;

    @Autowired
    public SimpleEntitySerialization(EntityTypeRegistry entityTypeRegistry) {
        this.entityTypeRegistry = entityTypeRegistry;
    }

    @Override
    public ByteBuf serializeEntity(Entity entity) throws IOException {
        InOutCodec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entity);
        EntityType entityType = entityTypeRegistry.getEntityType(entity);
        ByteBuf data = Unpooled.buffer();

        ByteBufUtils.writeVarInt(data, entityType.getTypeID());
        ByteBuf encoded = entityCodec.encode(entity);
        data.writeBytes(encoded);
        encoded.release();

        return data;
    }

    @Override
    public Entity deserializeEntity(ByteBuf data) throws IOException {
        int typeID = ByteBufUtils.readVarInt(data);

        EntityType entityType = entityTypeRegistry.getEntityType(typeID);
        InOutCodec<Entity, ByteBuf> entityCodec = entityTypeRegistry.getEntityCodec(entityType);

        return entityCodec.decode(data);
    }
}
