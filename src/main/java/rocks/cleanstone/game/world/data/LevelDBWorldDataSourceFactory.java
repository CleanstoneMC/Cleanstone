package rocks.cleanstone.game.world.data;

import java.io.File;
import java.io.IOException;

public interface LevelDBWorldDataSourceFactory {

    LevelDBWorldDataSource get(File worldDataFolder, String worldID) throws IOException;
}
