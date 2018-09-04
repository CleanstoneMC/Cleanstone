package rocks.cleanstone.game.world.data;

import java.io.IOException;

public interface WorldDataSourceFactory {
    WorldDataSource get(String worldID) throws IOException;
}
