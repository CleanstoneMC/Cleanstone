package rocks.cleanstone.net.bedrock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.net.bedrock.protocol.SimpleBedrockProtocol;
import rocks.cleanstone.net.raknet.RakNetNetworking;

@Component
@ConditionalOnProperty(name = "minecraft.bedrock.enabled", havingValue = "true")
//TODO: Change code to not load the package at all
public class SimpleBedrockNetworking extends RakNetNetworking implements BedrockNetworking {

    private final StatusProvider statusProvider;
    private final MinecraftConfig minecraftConfig;

    @Autowired
    public SimpleBedrockNetworking(MinecraftConfig minecraftConfig, SimpleBedrockProtocol bedrockProtocol,
                                   StatusProvider statusProvider) {
        super(minecraftConfig.getBedrock().getPort(), minecraftConfig.getBedrock().getAddress(), bedrockProtocol, statusProvider);
        this.minecraftConfig = minecraftConfig;
        this.statusProvider = statusProvider;
    }

    @Override
    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
