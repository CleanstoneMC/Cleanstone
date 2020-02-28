package rocks.cleanstone.storage.player;

public interface PlayerDataSourceFactory {

    String getName();

    PlayerDataSource get() throws PlayerDataSourceCreationException;
}
