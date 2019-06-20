package rocks.cleanstone.storage.engine.noop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;

@Slf4j
@Component
@ConditionalOnProperty(name = "world.datasource", havingValue = "noop")
public class NoOpWorldDataSourceFactory implements WorldDataSourceFactory {

    @Override
    public WorldDataSource get(String worldID) {
        return new NoOpWorldDataSource();
    }
}
