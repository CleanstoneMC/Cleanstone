package rocks.cleanstone.endpoint.minecraft.java.net;

import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public interface MinecraftNetworking extends Networking {
    MinecraftConfig getMinecraftConfig();
}
