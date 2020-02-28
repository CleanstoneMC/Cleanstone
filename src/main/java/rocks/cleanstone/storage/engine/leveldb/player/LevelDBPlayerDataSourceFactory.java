package rocks.cleanstone.storage.engine.leveldb.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
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
@ConditionalOnJndi("leveldbjni")
public class LevelDBPlayerDataSourceFactory implements PlayerDataSourceFactory {


    public LevelDBPlayerDataSourceFactory() {
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
        return "leveldb";
    }

    @Override
    public PlayerDataSource get() throws PlayerDataSourceCreationException {
        try {
            return new LevelDBPlayerDataSource(getPlayerDataFolder());
        } catch (IOException e) {
            throw new PlayerDataSourceCreationException(e);
        }
    }
}
