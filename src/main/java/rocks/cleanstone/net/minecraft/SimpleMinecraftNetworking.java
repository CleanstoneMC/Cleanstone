package rocks.cleanstone.net.minecraft;

import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;

import java.net.InetAddress;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    private final LoginManager loginManager;

    public SimpleMinecraftNetworking(int port, InetAddress address) {
        super(port, address, new SimpleMinecraftProtocol());
        this.loginManager = new LoginManager(this);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
