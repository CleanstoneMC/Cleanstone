package rocks.cleanstone.net.minecraft.entity.data;

import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;

import rocks.cleanstone.net.protocol.ServerProtocolLayer;

public interface ProtocolEntitiesDataLoader {
    ListenableFuture<ProtocolEntitiesData> loadData(ServerProtocolLayer protocolLayer) throws IOException;
}
