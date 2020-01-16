package rocks.cleanstone.endpoint.minecraft.java.net.entity.data;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

import java.io.IOException;

public interface ProtocolEntitySchemeLoader {
    ProtocolEntityScheme loadData(ClientProtocolLayer protocolLayer) throws IOException;
}
