package rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.data;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

import java.io.IOException;

public interface ProtocolEntitySchemeLoader {
    ProtocolEntityScheme loadData(ClientProtocolLayer protocolLayer) throws IOException;
}
