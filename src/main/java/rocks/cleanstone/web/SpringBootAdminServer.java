package rocks.cleanstone.web;

import de.codecentric.boot.admin.server.config.AdminServerMarkerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rocks.cleanstone.web.config.WebConfig;

import java.util.concurrent.Executors;

@SpringBootApplication
@EnableConfigurationProperties
@ConditionalOnProperty(name = "web.enabled", havingValue = "true") //TODO: Dont start webserver when false
public class SpringBootAdminServer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, WebMvcConfigurer {
    private final WebConfig webConfig;

    @Autowired
    public SpringBootAdminServer(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    @Bean
    public AdminServerMarkerConfiguration.Marker adminServerMarker() {
        return new AdminServerMarkerConfiguration.Marker();
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5)));
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(webConfig.getServer().getPort());
        server.setAddress(webConfig.getServer().getAddress());
    }
}
