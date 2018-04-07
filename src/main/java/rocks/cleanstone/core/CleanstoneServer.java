package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.net.Networking;

public class CleanstoneServer {

    @Autowired
    private Networking networking;

    @Autowired
    private PlayerCommunicationManager playerCommunicationManager;

    @Autowired
    private ServerCommunicationManager serverCommunicationManager;



    public void init() {
        System.out.println("init");
    }

    public void run() {
        System.out.println("run");
        networking.test();
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
