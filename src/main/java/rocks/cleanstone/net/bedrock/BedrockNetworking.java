package rocks.cleanstone.net.bedrock;

import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public interface BedrockNetworking extends Networking {
    MinecraftConfig getMinecraftConfig();
}
