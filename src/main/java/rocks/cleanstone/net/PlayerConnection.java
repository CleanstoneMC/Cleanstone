package rocks.cleanstone.net;

import java.net.InetAddress;

public abstract class PlayerConnection extends Connection {

    private final InetAddress address;

    private boolean compressionEnabled = false;

    public PlayerConnection(InetAddress address) {
        super(address);
        this.address = address;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }


}
