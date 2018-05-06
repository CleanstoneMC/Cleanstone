package rocks.cleanstone.net.minecraft;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final MinecraftConfig minecraftConfig;

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol) {
        super(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol);
        this.minecraftConfig = minecraftConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
