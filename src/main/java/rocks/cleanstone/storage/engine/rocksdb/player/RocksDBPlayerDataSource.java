package rocks.cleanstone.storage.engine.rocksdb.player;

import io.netty.buffer.ByteBuf;
import org.rocksdb.RocksDBException;
import rocks.cleanstone.data.InboundCodec;
import rocks.cleanstone.data.JavaSerializableCodec;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.data.PlayerDataType;
import rocks.cleanstone.storage.engine.rocksdb.RocksDBDataSource;
import rocks.cleanstone.storage.player.PlayerDataSource;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

public class RocksDBPlayerDataSource extends RocksDBDataSource implements PlayerDataSource {

    public RocksDBPlayerDataSource(Path playerDataFolder) throws RocksDBException {
        super(playerDataFolder);
    }

    public <T> T getPlayerData(Player player, PlayerDataType type, InboundCodec<T, ByteBuf> codec)
            throws IOException {
        ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
        try {
            return get(playerDataKey, codec);
        } finally {
            playerDataKey.release();
        }
    }

    public <T> void setPlayerData(Player player, PlayerDataType type, T playerData, OutboundCodec<T, ByteBuf> codec)
            throws IOException {
        ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
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
