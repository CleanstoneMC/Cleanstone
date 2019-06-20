package rocks.cleanstone.storage.world;

public interface WorldDataSourceFactory {
    WorldDataSource get(String worldID) throws WorldDataSourceCreationException;
}
