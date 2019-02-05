package rocks.cleanstone.net.minecraft.entity.data;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import rocks.cleanstone.net.protocol.ServerProtocolLayer;

@Component
public class SimpleProtocolEntitySchemeLoader implements ProtocolEntitySchemeLoader {

    private final ResourceLoader resourceLoader;

    @Autowired
    public SimpleProtocolEntitySchemeLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ProtocolEntityScheme loadData(ServerProtocolLayer serverLayer) throws IOException {
        String protocolVersion = serverLayer.getCorrespondingClientLayer().getName().replace(".", "_");

        Resource entitiesDataResource = resourceLoader.getResource(
                "classpath:protocol/v" + protocolVersion + "/entities.json");
        if (!entitiesDataResource.exists()) {
            throw new IllegalArgumentException(
                    "ProtocolEntityScheme (entities.json) not found for protocol version " + protocolVersion);
        }

        try (Reader reader = new InputStreamReader(entitiesDataResource.getInputStream(), Charsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, ProtocolEntityScheme.class);
        } catch (JsonParseException e) {
            throw new IOException(e);
        }
    }
}
