package rocks.cleanstone.net.mcpe;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public interface MCPENetworking extends Networking {
    MinecraftConfig getMinecraftConfig();
}
