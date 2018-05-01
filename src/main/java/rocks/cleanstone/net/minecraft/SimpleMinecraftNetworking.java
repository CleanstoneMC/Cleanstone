package rocks.cleanstone.net.minecraft;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;

import java.net.InetAddress;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final LoginManager loginManager;

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol) {
        super(port, address, protocol);
        this.loginManager = new LoginManager(this);
    }

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol) {
        super(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol);
        this.loginManager = new LoginManager(this);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
