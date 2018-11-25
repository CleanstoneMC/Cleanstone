package rocks.cleanstone.game.entity.codec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.SimpleLivingEntity;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SimpleLivingEntityCodec implements InOutCodec<SimpleLivingEntity, ByteBuf> {

    private final EntityCodec entityCodec;

    @Autowired
    public SimpleLivingEntityCodec(EntityCodec entityCodec) {
        this.entityCodec = entityCodec;
    }

    @Override
    public SimpleLivingEntity decode(ByteBuf data) throws IOException {
        Entity entity = entityCodec.decode(data);
        float yaw = data.readFloat(), pitch = data.readFloat();
        HeadRotatablePosition position = new HeadRotatablePosition(entity.getPosition(),
                new Rotation(yaw, pitch));
        int health = ByteBufUtils.readVarInt(data);
        return new SimpleLivingEntity(entity.getWorld(), position, true, entity.isGlowing(), health);
    }

    @Override
    public ByteBuf encode(SimpleLivingEntity livingEntity) throws IOException {
        ByteBuf data = entityCodec.encode(livingEntity);
        data.writeFloat(livingEntity.getPosition().getHeadRotation().getYaw());
        data.writeFloat(livingEntity.getPosition().getHeadRotation().getPitch());
        ByteBufUtils.writeVarInt(data, livingEntity.getHealth());
        return data;
    }
}
