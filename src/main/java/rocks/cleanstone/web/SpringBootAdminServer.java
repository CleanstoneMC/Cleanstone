package rocks.cleanstone.web;

import de.codecentric.boot.admin.server.config.AdminServerMarkerConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executors;

@Configuration
@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootAdminServer implements WebMvcConfigurer {
    @Bean
    @ConditionalOnProperty("web.admin.enabled")
    public AdminServerMarkerConfiguration.Marker adminServerMarker() {
        return new AdminServerMarkerConfiguration.Marker();
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5)));
    }
}
