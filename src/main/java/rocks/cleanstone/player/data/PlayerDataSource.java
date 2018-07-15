package rocks.cleanstone.player.data;

import java.io.IOException;
import java.io.Serializable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.player.Player;

public interface PlayerDataSource {
    <T> T getPlayerData(Player player, PlayerDataType type, Codec<T, ByteBuf> codec) throws IOException;

    <T> void setPlayerData(Player player, PlayerDataType type, T playerData, Codec<T, ByteBuf> codec)
            throws IOException;

    <T extends Serializable> T getPlayerData(Player player, PlayerDataType type) throws IOException;

    <T extends Serializable> void setPlayerData(Player player, PlayerDataType type, T playerData)
            throws IOException;

    void close();
}
