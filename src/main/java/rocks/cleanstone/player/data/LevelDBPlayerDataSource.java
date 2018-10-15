package rocks.cleanstone.player.data;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InboundCodec;
import rocks.cleanstone.data.JavaSerializableCodec;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.player.Player;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

public class LevelDBPlayerDataSource extends LevelDBDataSource implements PlayerDataSource {

    public LevelDBPlayerDataSource(Path playerDataFolder) throws IOException {
        super(playerDataFolder);
    }

    public <T> T getPlayerData(Player player, PlayerDataType type, InboundCodec<T, ByteBuf> codec)
            throws IOException {
        final ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
        try {
            return get(playerDataKey, codec);
        } finally {
            playerDataKey.release();
        }
    }

    public <T> void setPlayerData(Player player, PlayerDataType type, T playerData, OutboundCodec<T, ByteBuf> codec)
            throws IOException {
        final ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
        try {
            set(playerDataKey, playerData, codec);
        } finally {
            playerDataKey.release();
        }
    }

    public <T extends Serializable> T getPlayerData(Player player, PlayerDataType type) throws IOException {
        return getPlayerData(player, type, new JavaSerializableCodec<>());
    }

    public <T extends Serializable> void setPlayerData(Player player, PlayerDataType type, T playerData)
            throws IOException {
        setPlayerData(player, type, playerData, new JavaSerializableCodec<>());
    }
}
