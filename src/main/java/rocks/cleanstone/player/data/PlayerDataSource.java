package rocks.cleanstone.player.data;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.io.Serializable;

import rocks.cleanstone.data.InboundCodec;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.player.Player;

public interface PlayerDataSource {
    <T> T getPlayerData(Player player, PlayerDataType type, InboundCodec<T, ByteBuf> codec) throws IOException;

    <T> void setPlayerData(Player player, PlayerDataType type, T playerData, OutboundCodec<T, ByteBuf> codec)
            throws IOException;

    <T extends Serializable> T getPlayerData(Player player, PlayerDataType type) throws IOException;

    <T extends Serializable> void setPlayerData(Player player, PlayerDataType type, T playerData)
            throws IOException;

    void close();
}
