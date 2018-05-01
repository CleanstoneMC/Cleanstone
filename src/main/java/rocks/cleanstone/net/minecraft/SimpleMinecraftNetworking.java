package rocks.cleanstone.net.minecraft;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

import java.net.InetAddress;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    @Autowired
    private LoginManager loginManager;

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol) {
        super(port, address, protocol);
        loginManager.setNetworking(this);
    }

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol) {
        this(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
