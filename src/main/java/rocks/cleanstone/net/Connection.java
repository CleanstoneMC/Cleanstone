package rocks.cleanstone.net;

import java.net.InetAddress;

public class Connection {
    private final InetAddress address;

    private boolean compressionEnabled = false;

    public Connection(InetAddress address) {
        this.address = address;
    }

    public InetAddress getAddress() {
        return address;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }
}
