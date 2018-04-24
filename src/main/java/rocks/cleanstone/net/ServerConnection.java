package rocks.cleanstone.net;

import io.netty.channel.Channel;

import java.net.InetAddress;

public abstract class ServerConnection extends Connection {

    public ServerConnection(InetAddress address, Channel channel) {
        super(address, channel);
    }
}
