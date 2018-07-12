package rocks.cleanstone;

import org.springframework.boot.SpringApplication;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.core.CleanstoneSubServer;

public class Cleanstone {
    private static String[] startupArgs;

    public static void main(String[] args) {
        startupArgs = args;
        start();
    }

    public static void start() {
        if (startupArgs.length > 0 && startupArgs[0].equalsIgnoreCase("subserver")) {
            SpringApplication.run(CleanstoneSubServer.class, startupArgs);
        } else {
            SpringApplication.run(CleanstoneMainServer.class, startupArgs);
        }
    }
}
