package rocks.cleanstone;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.CleanstoneSubServer;

public class Cleanstone {

    public static void main(String[] args) {
        ConfigurableApplicationContext context;
        if (args.length > 0 && args[0].equalsIgnoreCase("subserver")) {
            context = SpringApplication.run(CleanstoneSubServer.class, args);
        } else {
            context = SpringApplication.run(CleanstoneMainServer.class, args);
        }
        CleanstoneServer.getInstance().context = context;
        CleanstoneServer.getInstance().context.registerShutdownHook();
    }
}
