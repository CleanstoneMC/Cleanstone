package rocks.cleanstone.player.data;

import java.io.File;
import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.player.Player;

public class LevelDBPlayerDataSource extends LevelDBDataSource implements PlayerDataSource {

    public LevelDBPlayerDataSource(File playerDataFolder) throws IOException {
        super(playerDataFolder);
    }

    public <T> T getPlayerData(Player player, PlayerDataType type, Codec<T, ByteBuf> codec)
            throws IOException {
        ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
        try {
            return get(playerDataKey, codec);
        } finally {
            playerDataKey.release();
        }
    }

    public <T> void setPlayerData(Player player, PlayerDataType type, T playerData, Codec<T, ByteBuf> codec)
            throws IOException {
        ByteBuf playerDataKey = PlayerDataKeyFactory.create(player, type);
        try {
            set(playerDataKey, playerData, codec);
        } finally {
            playerDataKey.release();
        }
    }
}
