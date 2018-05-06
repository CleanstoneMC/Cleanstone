package rocks.cleanstone.net.minecraft;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public interface MinecraftNetworking extends Networking {
    MinecraftConfig getMinecraftConfig();
}
