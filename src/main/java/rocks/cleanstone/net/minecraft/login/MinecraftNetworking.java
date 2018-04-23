package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.net.packet.protocol.minecraft.SimpleMinecraftProtocol;

import java.net.InetAddress;

public class MinecraftNetworking extends NettyNetworking {

    private final LoginManager loginManager;

    public MinecraftNetworking(int port, InetAddress address) {
        super(port, address, new SimpleMinecraftProtocol());
        this.loginManager = new LoginManager(this);
    }
}
