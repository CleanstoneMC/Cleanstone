package rocks.cleanstone.net.minecraft.entity.data;

import java.io.IOException;

import rocks.cleanstone.net.protocol.ServerProtocolLayer;

public interface ProtocolEntitySchemeLoader {
    ProtocolEntityScheme loadData(ServerProtocolLayer protocolLayer) throws IOException;
}
