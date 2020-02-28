package rocks.cleanstone.storage.world;

public interface WorldDataSourceFactory {

    String getName();

    WorldDataSource get(String worldID) throws WorldDataSourceCreationException;
}
