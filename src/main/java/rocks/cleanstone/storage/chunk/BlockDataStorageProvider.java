package rocks.cleanstone.storage.chunk;

import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.engine.simple.SimpleBlockDataStorage;

@Component
public class BlockDataStorageProvider {

    public BlockDataStorage getBlockDataStorage() {
        return new SimpleBlockDataStorage(true);
    }
}
