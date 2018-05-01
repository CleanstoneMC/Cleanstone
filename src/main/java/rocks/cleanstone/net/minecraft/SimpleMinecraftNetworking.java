package rocks.cleanstone.net.minecraft;

import java.net.InetAddress;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.minecraft.status.StatusRequestListener;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final LoginManager loginManager;
    private final StatusRequestListener statusRequestListener;

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol,
                                     LoginManager loginManager, StatusRequestListener statusRequestListener) {
        super(port, address, protocol);
        this.loginManager = loginManager;
        this.statusRequestListener = statusRequestListener;
        loginManager.setNetworking(this);
    }

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol,
                                     LoginManager loginManager, StatusRequestListener statusRequestListener) {
        this(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol, loginManager, statusRequestListener);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
