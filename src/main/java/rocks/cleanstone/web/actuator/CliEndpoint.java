package rocks.cleanstone.web.actuator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.ConsoleInputEvent;

@Component
@Endpoint(id = "cli")
public class CliEndpoint {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @WriteOperation
    public void sendCommand(String command) {
        logger.info("CLI issued command: " + command + "");
        CleanstoneServer.publishEvent(new ConsoleInputEvent(command));
    }

}
