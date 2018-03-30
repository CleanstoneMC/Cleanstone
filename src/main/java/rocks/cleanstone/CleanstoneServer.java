package rocks.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.net.NetworkInterface;

public class CleanstoneServer {

    @Autowired
    private NetworkInterface networkInterface;

    public void init() {
        System.out.println("init");
    }

    public void run() {
        System.out.println("run");
        networkInterface.test();
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
