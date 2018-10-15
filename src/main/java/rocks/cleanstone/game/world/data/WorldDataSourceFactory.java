package rocks.cleanstone.game.world.data;

public interface WorldDataSourceFactory {
    WorldDataSource get(String worldID) throws WorldDataSourceCreationException;
}
