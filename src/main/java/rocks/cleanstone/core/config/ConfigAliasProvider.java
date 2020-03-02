package rocks.cleanstone.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ConfigAliasProvider implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Map<String, String> aliases = new HashMap<>();

    static {
        aliases.put("spring.boot.admin.client.url", "web.client.url");
        aliases.put("spring.boot.admin.ui.public-url", "web.server.url");
    }

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application to configure
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.getEnvironment().getPropertySources().addFirst(new PropertySource<>("aliasConfig") {
            @Override
            @Nullable
            public Object getProperty(@NonNull String name) {
                if (!aliases.containsKey(name)) {
                    return null;
                }

                return applicationContext.getEnvironment().getProperty(aliases.get(name));
            }
        });
    }

}
