package rocks.cleanstone.web;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SpringBootUrlReplacer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final Map<String, Object> properties = new HashMap<>();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        String springBootAdminServerUrl = environment.getProperty("web.client.url");
        if (environment.getProperty("web.admin.enabled", Boolean.class, false)) {
            springBootAdminServerUrl = "http://" + environment.getProperty("web.server.address") + ":" + environment.getProperty("web.server.port");
        }
        properties.put("spring.boot.admin.client.url", springBootAdminServerUrl);

        properties.put("spring.boot.admin.ui.public-url", environment.getProperty("web.admin.url"));
        properties.put("server.port", environment.getProperty("web.server.port"));
        properties.put("server.address", environment.getProperty("web.server.address"));

        environment.getPropertySources().addFirst(new PropertySource<>("spring-boot-admin-property-source") {
            @Override
            public Object getProperty(@NonNull String name) {
                if (properties.containsKey(name)) {
                    return properties.get(name);
                }
                return null;
            }
        });
    }

}
