package rocks.cleanstone.player.data.standard;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EntityDataCodec implements Codec<EntityData, ByteBuf> {

    @Override
    public EntityData deserialize(ByteBuf data) throws IOException {

        String logoutWorldID = ByteBufUtils.readUTF8(data);
        Position position = new Position(ByteBufUtils.readVector(data));
        float yaw = data.readFloat(), pitch = data.readFloat();
        Rotation rotation = new Rotation(yaw, pitch);
        RotatablePosition logoutPosition = new RotatablePosition(position, rotation);

        GameMode gameMode = VanillaGameMode.byID(ByteBufUtils.readVarInt(data));

        return new EntityData(logoutPosition, logoutWorldID, gameMode);
    }

    @Override
    public ByteBuf serialize(EntityData value) throws IOException {
        ByteBuf data = Unpooled.buffer();

        RotatablePosition logoutPosition = value.getLogoutPosition();
        ByteBufUtils.writeUTF8(data, value.getLogoutWorldID());
        ByteBufUtils.writeVector(data, logoutPosition.toVector());
        data.writeFloat(logoutPosition.getRotation().getYaw());
        data.writeFloat(logoutPosition.getRotation().getPitch());

        ByteBufUtils.writeVarInt(data, value.getGameMode().getTypeId());
        return data;
    }
}
