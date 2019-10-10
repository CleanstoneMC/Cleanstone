package rocks.cleanstone.core.config;


import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SimpleConfigLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static String SIMPLE_CONFIG = "config.yml";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Path fileSystemConfig = Paths.get(SIMPLE_CONFIG);
            boolean exists = Files.exists(fileSystemConfig);
            if (!exists) {
                InputStream classPathConfig = new ClassPathResource(SIMPLE_CONFIG).getInputStream();

                Files.copy(classPathConfig, fileSystemConfig);
            }

            Resource resource = applicationContext.getResource("file:./" + SIMPLE_CONFIG);
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> propertySources = sourceLoader.load("simpleConfig", resource);
            propertySources.forEach(propertySource -> applicationContext.getEnvironment().getPropertySources().addFirst(propertySource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}