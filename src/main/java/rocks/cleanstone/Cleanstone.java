package rocks.cleanstone;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.CleanstoneSubServer;

public class Cleanstone {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("rocks/cleanstone/CleanstoneServer.xml");
        context.registerShutdownHook();
        Scope threadScope = new SimpleThreadScope();
        context.getBeanFactory().registerScope("thread", threadScope);

        CleanstoneServer cleanstoneServer;
        if (args.length >= 1 && args[0].equalsIgnoreCase("subserver")) {
            cleanstoneServer = (CleanstoneSubServer) context.getBean("cleanstoneSubServer");
        } else {
            cleanstoneServer = (CleanstoneMainServer) context.getBean("cleanstoneMainServer");
        }
        cleanstoneServer.run();
    }
}
