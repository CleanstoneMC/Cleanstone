package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;

public class CleanstoneSubServer extends CleanstoneServer {

    private ExternalServer mainServer;

    public void run() {
        System.out.println("run");
        networking.test();
    }
}
