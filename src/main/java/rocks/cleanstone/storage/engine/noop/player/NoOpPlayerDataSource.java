package rocks.cleanstone.storage.engine.noop.player;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InboundCodec;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.data.PlayerDataType;
import rocks.cleanstone.storage.player.PlayerDataSource;

import java.io.IOException;
import java.io.Serializable;

public class NoOpPlayerDataSource implements PlayerDataSource {

    @Override
    public <T> T getPlayerData(Player player, PlayerDataType type, InboundCodec<T, ByteBuf> codec) throws IOException {
        return null;
    }

    @Override
    public <T> void setPlayerData(Player player, PlayerDataType type, T playerData, OutboundCodec<T, ByteBuf> codec) throws IOException {

    }

    @Override
    public <T extends Serializable> T getPlayerData(Player player, PlayerDataType type) throws IOException {
        return null;
    }

    @Override
    public <T extends Serializable> void setPlayerData(Player player, PlayerDataType type, T playerData) throws IOException {

    }

    @Override
    public void close() {

    }
}
