package rocks.cleanstone.storage.engine.noop.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.player.PlayerDataSource;
import rocks.cleanstone.storage.player.PlayerDataSourceCreationException;
import rocks.cleanstone.storage.player.PlayerDataSourceFactory;

@Slf4j
@Component
public class NoOpPlayerDataSourceFactory implements PlayerDataSourceFactory {

    @Override
    public String getName() {
        return "noop";
    }

    @Override
    public PlayerDataSource get() throws PlayerDataSourceCreationException {
        return new NoOpPlayerDataSource();
    }
}
