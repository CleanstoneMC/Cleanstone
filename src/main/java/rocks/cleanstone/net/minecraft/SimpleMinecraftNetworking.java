package rocks.cleanstone.net.minecraft;

import org.springframework.beans.factory.annotation.Autowired;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.player.PlayerManager;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol,
                                     PlayerManager playerManager) {
        super(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol, playerManager);
        this.minecraftConfig = minecraftConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
