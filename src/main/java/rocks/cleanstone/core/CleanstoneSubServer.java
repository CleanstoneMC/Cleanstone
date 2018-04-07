package rocks.cleanstone.core;

public class CleanstoneSubServer extends CleanstoneServer {
    public void run() {
        System.out.println("run");
        networking.test();
    }
}
