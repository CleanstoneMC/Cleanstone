package rocks.cleanstone.web.actuator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.logging.LogFile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import rocks.cleanstone.console.ConsoleSender;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Endpoint(id = "console")
public class ConsoleEndpoint {

    private final Environment environment;
    private final ConsoleSender consoleSender;

    private Path externalFile;
    private CommandRegistry commandRegistry;

    public ConsoleEndpoint(Environment environment, Path externalFile, CommandRegistry commandRegistry) {
        this.environment = environment;
        this.externalFile = externalFile;
        this.commandRegistry = commandRegistry;
        this.consoleSender = new ConsoleSender(s -> {
        });
    }

    @Autowired
    public ConsoleEndpoint(Environment environment, CommandRegistry commandRegistry) {
        this(environment, null, commandRegistry);
    }

    @WriteOperation
    public void sendCommand(String commandString) {
        log.info("WebConsole: " + commandString + "");

        CommandMessage commandMessage = CommandMessageFactory.construct(consoleSender, commandString, this.commandRegistry);

        Command command = commandRegistry.getCommand(commandMessage.getCommandName());
        if (command != null) {
            try {
                command.execute(commandMessage);
            } catch (Exception e) {
                log.error("Error executing Command: []", e);
            }
        }
    }

    /**
     * Returns a reversed Output from the Logfile.
     *
     * @return
     * @throws IOException
     */
    @ReadOperation
    public List<String> getLog() throws IOException {
        Resource logFileResource = getLogFileResource();
        if (logFileResource == null || !logFileResource.isReadable()) {
            return null;
        }

        ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(logFileResource.getFile(), Charset.forName("UTF-8"));

        List<String> log = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            final String line = reversedLinesFileReader.readLine();

            if (line.startsWith("\t") || line.isEmpty()) { //Remove Stacktrace and Empty lines
                i--;
                continue;
            }

            log.add(line);
        }

        reversedLinesFileReader.close();

        return log;
    }

    private Resource getLogFileResource() {
        if (this.externalFile != null) {
            return new FileSystemResource(this.externalFile.toFile());
        }
        LogFile logFile = LogFile.get(this.environment);
        if (logFile == null) {
            log.debug("Missing 'logging.file' or 'logging.path' properties");
            return null;
        }
        return new FileSystemResource(logFile.toString());
    }

}
