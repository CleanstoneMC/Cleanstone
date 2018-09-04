package rocks.cleanstone.game.world.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "world.datasource", havingValue = "noop")
public class NoOpWorldDataSourceFactory implements WorldDataSourceFactory {
    private final Logger logger = LoggerFactory.getLogger(NoOpWorldDataSourceFactory.class);

    @Override
    public WorldDataSource get(String worldID) {
        return new NoOpWorldDataSource();
    }
}
