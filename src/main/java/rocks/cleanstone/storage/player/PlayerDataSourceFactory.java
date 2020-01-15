package rocks.cleanstone.storage.player;

public interface PlayerDataSourceFactory {
    PlayerDataSource get() throws PlayerDataSourceCreationException;
}
