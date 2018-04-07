package rocks.cleanstone.net.netty;

import rocks.cleanstone.net.AbstractNetworking;

/**
 * Coded by fionera.
 */
public class NettyNetworking extends AbstractNetworking {

    public NettyNetworking(int port) {
        super(port);
        System.out.println("bla");
    }

    @Override
    public void test() {
        System.out.println("works: " + this.port);
    }
}
