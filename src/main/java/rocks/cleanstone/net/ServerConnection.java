package rocks.cleanstone.net;

import java.net.InetAddress;

public abstract class ServerConnection extends Connection {
    public ServerConnection(InetAddress address) {
        super(address);
    }
}
