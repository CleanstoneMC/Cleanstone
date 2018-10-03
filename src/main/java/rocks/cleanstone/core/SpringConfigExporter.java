package rocks.cleanstone.core;

import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
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
        Path fileSystemConfig = Paths.get("application.yml");

        boolean exists = Files.exists(fileSystemConfig);
        if (!exists) {
            InputStream classPathConfig = new ClassPathResource("application.yml").getInputStream();

            Files.copy(classPathConfig, fileSystemConfig);
            logger.info("Exported Application Config");
        }
    }
}
