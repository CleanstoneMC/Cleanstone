package rocks.cleanstone.net;

import java.net.InetAddress;

import io.netty.channel.Channel;

public abstract class PlayerConnection extends Connection {

    public PlayerConnection(InetAddress address, Channel channel) {
        super(address, channel);
    }
}
