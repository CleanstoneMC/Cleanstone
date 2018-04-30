package rocks.cleanstone.core;

import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import rocks.cleanstone.core.config.CleanstoneConfig;

@SpringBootApplication
public class CleanstoneApplication implements ApplicationRunner {

    private final CleanstoneConfig cleanstoneConfig;

    public CleanstoneApplication(CleanstoneConfig cleanstoneConfig) {
        this.cleanstoneConfig = cleanstoneConfig;
    }

    @Override
    public void run(ApplicationArguments args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("rocks/cleanstone/CleanstoneServer.xml");
        CleanstoneServer cleanstoneServer;

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
}
