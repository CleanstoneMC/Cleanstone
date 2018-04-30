package rocks.cleanstone.core;

import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

@SpringBootApplication
public class CleanstoneApplication implements ApplicationRunner {
    private static CleanstoneApplication INSTANCE;

    private final CleanstoneConfig cleanstoneConfig;
    private final MinecraftConfig minecraftConfig;
    private CleanstoneServer cleanstoneServer;

    public CleanstoneApplication(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        this.cleanstoneConfig = cleanstoneConfig;
        this.minecraftConfig = minecraftConfig;
    }

    @Override
    public void run(ApplicationArguments args) {
        INSTANCE = this;

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("rocks/cleanstone/CleanstoneServer.xml");

        if (args.containsOption("subserver")) {
            cleanstoneServer = (CleanstoneSubServer) context.getBean("cleanstoneSubServer");
        } else {
            cleanstoneServer = (CleanstoneMainServer) context.getBean("cleanstoneMainServer");
        }

        context.registerShutdownHook();
        Scope threadScope = new SimpleThreadScope();
        context.getBeanFactory().registerScope("thread", threadScope);

        cleanstoneServer.run();
    }

    public CleanstoneConfig getCleanstoneConfig() {
        return cleanstoneConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }

    public CleanstoneServer getCleanstoneServer() {
        return cleanstoneServer;
    }

    public static CleanstoneServer getInstance() {
        return INSTANCE.cleanstoneServer;
    }

    public static CleanstoneApplication getApplication() {
        return INSTANCE;
    }
}
