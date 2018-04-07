package rocks.cleanstone.net;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    public AbstractNetworking(int port) {
        this.port = port;
    }
}
