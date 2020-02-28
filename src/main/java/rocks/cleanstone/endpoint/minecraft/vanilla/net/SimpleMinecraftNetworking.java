package rocks.cleanstone.endpoint.minecraft.vanilla.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.config.MinecraftConfig;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.player.PlayerManager;

@Component
@ConditionalOnProperty(name = "endpoint.vanilla.enabled", havingValue = "true")
//TODO: Change code to not load the package at all
public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {
    @Autowired
    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol,
                                     PlayerManager playerManager) {
        super(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol, playerManager);
    }
}
