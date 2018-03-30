package rocks.cleanstone;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Cleanstone {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("rocks/cleanstone/CleanstoneServer.xml");
        CleanstoneServer cleanstoneServer = (CleanstoneServer) context.getBean("cleanstoneServer");

        cleanstoneServer.run();

        context.registerShutdownHook();
    }
}
