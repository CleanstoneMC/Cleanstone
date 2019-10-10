package rocks.cleanstone.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Profile;
import rocks.cleanstone.core.config.structs.WebConfig;

@SpringBootApplication
@Profile("mainServer")
@ConditionalOnProperty(name = "web.enabled", havingValue = "true") //TODO: Dont start webserver when false
public class SpringBootAdminServer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private final WebConfig webConfig;

    public SpringBootAdminServer(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(webConfig.getPort());
        server.setAddress(webConfig.getAddress());
    }
}
