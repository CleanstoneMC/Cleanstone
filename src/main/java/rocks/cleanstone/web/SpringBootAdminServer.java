package rocks.cleanstone.web;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableAdminServer
@Profile("mainServer")
public class SpringBootAdminServer {
}
