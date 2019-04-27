package rocks.cleanstone.core;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

/**
 * Component that exports the main configuration file from the java class path to the current working
 * directory to allow modifications by the server admin.
 */
@Slf4j
@Component
public class SpringConfigExporter {
    @PostConstruct
    public void afterStart() throws IOException {
        Path fileSystemConfig = Paths.get("application.yml");

        boolean exists = Files.exists(fileSystemConfig);
        if (!exists) {
            InputStream classPathConfig = new ClassPathResource("application.yml").getInputStream();

            Files.copy(classPathConfig, fileSystemConfig);
            log.info("Exported Application Config");
        }
    }
}
