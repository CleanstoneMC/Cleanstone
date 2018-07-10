package rocks.cleanstone.net.mcpe;

import org.springframework.beans.factory.annotation.Autowired;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.mcpe.protocol.SimpleMCPEProtocol;
import rocks.cleanstone.net.raknet.RakNetNetworking;

public class SimpleMCPENetworking extends RakNetNetworking implements MCPENetworking {

    private final StatusProvider statusProvider;
    private final MinecraftConfig minecraftConfig;

    @Autowired
    public SimpleMCPENetworking(MinecraftConfig minecraftConfig, SimpleMCPEProtocol mcpeProtocol,
                                StatusProvider statusProvider) {
        super(minecraftConfig.getMcpePort(), minecraftConfig.getMcpeAddress(), mcpeProtocol, statusProvider);
        this.minecraftConfig = minecraftConfig;
        this.statusProvider = statusProvider;
    }

    @Override
    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
