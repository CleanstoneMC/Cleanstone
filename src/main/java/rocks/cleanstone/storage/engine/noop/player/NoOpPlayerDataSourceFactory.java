package rocks.cleanstone.storage.engine.noop.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.player.PlayerDataSource;
import rocks.cleanstone.storage.player.PlayerDataSourceCreationException;
import rocks.cleanstone.storage.player.PlayerDataSourceFactory;

@Slf4j
@Component
@ConditionalOnProperty(name = "player.datasource", havingValue = "noop")
public class NoOpPlayerDataSourceFactory implements PlayerDataSourceFactory {

    @Override
    public PlayerDataSource get() throws PlayerDataSourceCreationException {
        return new NoOpPlayerDataSource();
    }
}
