package rocks.cleanstone.player.data.standard;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EntityDataCodec implements Codec<EntityData, ByteBuf> {

    private final WorldManager worldManager;
    private final OpenWorldGame game;

    public EntityDataCodec(OpenWorldGame game, WorldManager worldManager) {
        this.game = game;
        this.worldManager = worldManager;
    }

    @Override
    public EntityData deserialize(ByteBuf data) throws IOException {
        World world = worldManager.getLoadedWorld(ByteBufUtils.readUTF8(data));

        Location logoutLocation;
        if (world == null) {
            logoutLocation = game.getFirstSpawnWorld().getFirstSpawnLocation();
        } else {
            Position position = new Position(ByteBufUtils.readVector(data), world);
            float yaw = data.readFloat(), pitch = data.readFloat();
            Rotation rotation = new Rotation(yaw, pitch);
            logoutLocation = new Location(position, rotation);
        }

        GameMode gameMode = VanillaGameMode.byID(ByteBufUtils.readVarInt(data));

        return new EntityData(logoutLocation, gameMode);
    }

    @Override
    public ByteBuf serialize(EntityData value) throws IOException {
        ByteBuf data = Unpooled.buffer();

        Location logoutLocation = value.getLogoutLocation();
        ByteBufUtils.writeUTF8(data, logoutLocation.getPosition().getWorld().getID());
        ByteBufUtils.writeVector(data, logoutLocation.getPosition());
        data.writeFloat(logoutLocation.getRotation().getYaw());
        data.writeFloat(logoutLocation.getRotation().getPitch());

        ByteBufUtils.writeVarInt(data, value.getGameMode().getTypeId());
        return data;
    }
}
