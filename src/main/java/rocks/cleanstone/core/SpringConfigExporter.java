package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class SpringConfigExporter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void afterStart() throws IOException, URISyntaxException {
        Path classPathConfig = Paths.get(ClassLoader.getSystemResource("application.yml").toURI());
        Path fileSystemConfig = Paths.get("application.yml");

        boolean exists = Files.exists(fileSystemConfig);
        if (!exists) {
            Files.copy(classPathConfig, fileSystemConfig);
            logger.info("Exported Application Config");
        }
    }
}
