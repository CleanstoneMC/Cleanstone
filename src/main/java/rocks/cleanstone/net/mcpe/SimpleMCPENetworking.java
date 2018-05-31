package rocks.cleanstone.net.mcpe;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.mcpe.protocol.SimpleMCPEProtocol;
import rocks.cleanstone.net.raknet.RakNetNetworking;

public class SimpleMCPENetworking extends RakNetNetworking implements MCPENetworking {

    private final StatusProvider statusProvider;
    private final MinecraftConfig minecraftConfig;

    public SimpleMCPENetworking(MinecraftConfig minecraftConfig, SimpleMCPEProtocol protocol,
                                StatusProvider statusProvider) {
        super(minecraftConfig.getMcpePort(), minecraftConfig.getMcpeAddress(), protocol, statusProvider);
        this.minecraftConfig = minecraftConfig;
        this.statusProvider = statusProvider;
    }

    @Override
    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
