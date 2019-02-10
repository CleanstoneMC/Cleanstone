package rocks.cleanstone.net.minecraft.entity.data;

import java.io.IOException;

import rocks.cleanstone.net.protocol.ClientProtocolLayer;

public interface ProtocolEntitySchemeLoader {
    ProtocolEntityScheme loadData(ClientProtocolLayer protocolLayer) throws IOException;
}
