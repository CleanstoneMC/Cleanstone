package rocks.cleanstone;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class Cleanstone {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("rocks/cleanstone/CleanstoneServer.xml");
        context.registerShutdownHook();
        Scope threadScope = new SimpleThreadScope();
        context.getBeanFactory().registerScope("thread", threadScope);


        CleanstoneServer cleanstoneServer = (CleanstoneServer) context.getBean("cleanstoneServer");
        cleanstoneServer.run();
    }
}
