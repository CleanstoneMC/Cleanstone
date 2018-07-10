package rocks.cleanstone;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.CleanstoneSubServer;

public class Cleanstone {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("subserver")) {
            SpringApplication.run(CleanstoneSubServer.class, args).registerShutdownHook();
        } else {
            SpringApplication.run(CleanstoneMainServer.class, args).registerShutdownHook();
        }
    }
}
