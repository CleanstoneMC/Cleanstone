package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class CleanstoneMainServer extends CleanstoneServer {

    @Autowired
    private PlayerCommunicationManager playerCommunicationManager;

    @Autowired
    private PlayerManager playerManager;

    private Set<ExternalServer> externalServers;

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
