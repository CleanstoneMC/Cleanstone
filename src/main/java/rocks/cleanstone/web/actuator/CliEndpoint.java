package rocks.cleanstone.web.actuator;


import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Endpoint(id = "cli")
public class CliEndpoint {

    @ReadOperation
    public List<String> getLog() {
        return Collections.singletonList("Hello World");
    }

}
