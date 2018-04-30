package rocks.cleanstone.core;

public class CleanstoneSubServer extends CleanstoneServer {

    private ExternalServer mainServer;

    public void run() {
        System.out.println("run");
        cleanstoneNetworking.start();
    }
}
