package rocks.cleanstone.net;

/**
 * Coded by fionera.
 */
public abstract class AbstractNetwork implements NetworkInterface {

    protected final int port;

    public AbstractNetwork(int port) {
        this.port = port;
    }
}
