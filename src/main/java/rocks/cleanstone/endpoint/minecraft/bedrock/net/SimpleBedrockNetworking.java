package rocks.cleanstone.endpoint.minecraft.bedrock.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.bedrock.config.BedrockConfig;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.protocol.SimpleBedrockProtocol;
import rocks.cleanstone.net.raknet.RakNetNetworking;

@Component
@ConditionalOnProperty(name = "minecraft.bedrock.enabled", havingValue = "true")
//TODO: Change code to not load the package at all
public class SimpleBedrockNetworking extends RakNetNetworking implements BedrockNetworking {

    @Autowired
    public SimpleBedrockNetworking(BedrockConfig bedrockConfig, SimpleBedrockProtocol bedrockProtocol,
                                   StatusProvider statusProvider) {
        super(bedrockConfig.getPort(), bedrockConfig.getAddress(), bedrockProtocol, statusProvider);
    }
}
