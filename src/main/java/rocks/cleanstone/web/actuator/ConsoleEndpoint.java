package rocks.cleanstone.web.actuator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.ConsoleInputEvent;

@Slf4j
@Component
@Endpoint(id = "console")
public class ConsoleEndpoint {


    private final Environment environment;

    private final Path externalFile;

    public ConsoleEndpoint(Environment environment, Path externalFile) {
        this.environment = environment;
        this.externalFile = externalFile;
    }

    @Autowired
    public ConsoleEndpoint(Environment environment) {
        this(environment, null);
    }

    @WriteOperation
    public void sendCommand(String command) {
        log.info("Console: " + command + "");
        CleanstoneServer.publishEvent(new ConsoleInputEvent(command));
    }

    /**
     * Returns a reversed Output from the Logfile.
     *
     * @return
     * @throws IOException
     */
    @ReadOperation
    public List<String> getLog() throws IOException {
        final Resource logFileResource = getLogFileResource();
        if (logFileResource == null || !logFileResource.isReadable()) {
            return null;
        }

        final ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(logFileResource.getFile(), Charset.forName("UTF-8"));

        final List<String> log = new ArrayList<>();

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
        final LogFile logFile = LogFile.get(this.environment);
        if (logFile == null) {
            log.debug("Missing 'logging.file' or 'logging.path' properties");
            return null;
        }
        return new FileSystemResource(logFile.toString());
    }

}
