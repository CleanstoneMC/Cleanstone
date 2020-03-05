package rocks.cleanstone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import rocks.cleanstone.core.FullNameBeanNameGenerator;
import rocks.cleanstone.core.config.SimpleConfigLoader;
import rocks.cleanstone.web.SpringBootConditionFix;
import rocks.cleanstone.web.SpringBootUrlReplacer;

@SpringBootApplication
public class Cleanstone {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Cleanstone.class)
                .beanNameGenerator(new FullNameBeanNameGenerator())
                .initializers(new SimpleConfigLoader())
                .initializers(new SpringBootConditionFix())
                .initializers(new SpringBootUrlReplacer())
                .run(args).start();
    }
}
