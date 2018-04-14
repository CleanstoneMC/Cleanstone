package rocks.cleanstone.net;

import java.net.InetAddress;

public class PlayerConnection implements Connection {

    private final InetAddress address;

    public PlayerConnection(InetAddress address) {
        this.address = address;
    }
}
