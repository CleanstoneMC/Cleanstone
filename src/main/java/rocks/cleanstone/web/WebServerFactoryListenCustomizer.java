package rocks.cleanstone.web;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;
import rocks.cleanstone.web.config.WebConfig;

@Component
public class WebServerFactoryListenCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private final WebConfig webConfig;

    public WebServerFactoryListenCustomizer(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(webConfig.getServer().getPort());
        factory.setAddress(webConfig.getServer().getAddress());
    }
}
