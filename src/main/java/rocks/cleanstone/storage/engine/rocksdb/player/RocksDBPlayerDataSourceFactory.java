package rocks.cleanstone.storage.engine.rocksdb.player;

import lombok.extern.slf4j.Slf4j;
import org.rocksdb.RocksDBException;
import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.player.PlayerDataSource;
import rocks.cleanstone.storage.player.PlayerDataSourceCreationException;
import rocks.cleanstone.storage.player.PlayerDataSourceFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class RocksDBPlayerDataSourceFactory implements PlayerDataSourceFactory {


    public RocksDBPlayerDataSourceFactory() {
    }

    private Path getPlayerDataFolder() {
        Path dataFolder = Paths.get("data", "players");
        try {
            Files.createDirectories(dataFolder);
        } catch (IOException e) {
            log.error("Cannot create data folder (no permission?)", e);
        }
        return dataFolder;
    }

    @Override
    public String getName() {
        return "rocksdb";
    }

    @Override
    public PlayerDataSource get() throws PlayerDataSourceCreationException {
        try {
            return new RocksDBPlayerDataSource(getPlayerDataFolder());
        } catch (RocksDBException e) {
            throw new PlayerDataSourceCreationException(e);
        }
    }
}
