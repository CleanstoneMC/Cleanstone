package rocks.cleanstone.endpoint.minecraft.bedrock.net;

import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public interface BedrockNetworking extends Networking {
    MinecraftConfig getMinecraftConfig();
}
