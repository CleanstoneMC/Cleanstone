package rocks.cleanstone.net.minecraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.structs.MinecraftConfig;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.player.PlayerManager;

@Component
@ConditionalOnProperty(name = "minecraft.java.enabled", havingValue = "true")
//TODO: Change code to not load the package at all
public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final MinecraftConfig minecraftConfig;

    @Autowired
    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol,
                                     PlayerManager playerManager) {
        super(minecraftConfig.getJava().getPort(), minecraftConfig.getJava().getAddress(), protocol, playerManager);
        this.minecraftConfig = minecraftConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
