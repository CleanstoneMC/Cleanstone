package rocks.cleanstone.net.netty;

import rocks.cleanstone.net.AbstractNetwork;

/**
 * Coded by fionera.
 */
public class NettyNetwork extends AbstractNetwork {

    public NettyNetwork(int port) {
        super(port);
        System.out.println("bla");
    }

    @Override
    public void test() {
        System.out.println("works: " + this.port);
    }
}
