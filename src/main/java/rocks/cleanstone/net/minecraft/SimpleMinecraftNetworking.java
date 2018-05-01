package rocks.cleanstone.net.minecraft;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.minecraft.status.PingListener;
import rocks.cleanstone.net.minecraft.status.StatusRequestListener;
import rocks.cleanstone.net.netty.NettyNetworking;

import java.net.InetAddress;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final LoginManager loginManager;
    private final StatusRequestListener statusRequestListener;
    private final PingListener pingListener;

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol,
                                     LoginManager loginManager, StatusRequestListener statusRequestListener, PingListener pingListener) {
        super(port, address, protocol);
        this.loginManager = loginManager;
        this.statusRequestListener = statusRequestListener;
        this.pingListener = pingListener;
        loginManager.setNetworking(this);
    }

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol,
                                     LoginManager loginManager, StatusRequestListener statusRequestListener, PingListener pingListener) {
        this(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol, loginManager, statusRequestListener, pingListener);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
