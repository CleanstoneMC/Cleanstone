package rocks.cleanstone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import rocks.cleanstone.core.FullNameBeanNameGenerator;

@SpringBootApplication
public class Cleanstone {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Cleanstone.class).
                beanNameGenerator(new FullNameBeanNameGenerator())
                .run(args).start();
    }
}
