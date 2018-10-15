package rocks.cleanstone.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringConfigExporter {
    @PostConstruct
    public void afterStart() throws IOException {
        final Path fileSystemConfig = Paths.get("application.yml");

        final boolean exists = Files.exists(fileSystemConfig);
        if (!exists) {
            final InputStream classPathConfig = new ClassPathResource("application.yml").getInputStream();

            Files.copy(classPathConfig, fileSystemConfig);
            log.info("Exported Application Config");
        }
    }
}
