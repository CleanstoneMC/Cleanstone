package rocks.cleanstone.net.mcpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.mcpe.protocol.SimpleMCPEProtocol;
import rocks.cleanstone.net.raknet.RakNetNetworking;

@Component
public class SimpleMCPENetworking extends RakNetNetworking implements MCPENetworking {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public SimpleMCPENetworking(MinecraftConfig minecraftConfig, SimpleMCPEProtocol mcpeProtocol,
                                StatusProvider statusProvider) {
        super(minecraftConfig.getMcpePort(), minecraftConfig.getMcpeAddress(), mcpeProtocol, statusProvider);
        this.minecraftConfig = minecraftConfig;
        StatusProvider statusProvider1 = statusProvider;
    }

    @Override
    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
