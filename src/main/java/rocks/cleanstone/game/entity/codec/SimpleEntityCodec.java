package rocks.cleanstone.game.entity.codec;

import com.google.common.base.Preconditions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.SimpleEntity;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SimpleEntityCodec implements InOutCodec<SimpleEntity, ByteBuf> {

    private final WorldManager worldManager;

    @Autowired
    public SimpleEntityCodec(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public SimpleEntity decode(ByteBuf data) throws IOException {
        RotatablePosition rotatablePosition = ByteBufUtils.readRotatablePosition(data);
        String worldID = ByteBufUtils.readUTF8(data);
        World world = worldManager.getLoadedWorld(worldID);
        Preconditions.checkNotNull(world, "Cannot find world '" + worldID + "' while decoding entity");
        boolean isSpawnable = data.readBoolean(), isGlowing = data.readBoolean();
        return new SimpleEntity(world, rotatablePosition, true, isSpawnable, isGlowing);
    }

    @Override
    public ByteBuf encode(SimpleEntity entity) throws IOException {
        ByteBuf data = Unpooled.buffer();
        ByteBufUtils.writeRotatablePosition(data, entity.getPosition());
        ByteBufUtils.writeUTF8(data, entity.getWorld().getID());
        data.writeBoolean(entity.isSpawnable());
        data.writeBoolean(entity.isGlowing());
        return data;
    }
}
