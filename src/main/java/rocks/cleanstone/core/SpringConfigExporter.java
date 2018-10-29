package rocks.cleanstone.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class SpringConfigExporter {
    @PostConstruct
    public void afterStart() throws IOException, URISyntaxException {
        Path fileSystemConfig = Paths.get("application.yml");

        boolean exists = Files.exists(fileSystemConfig);
        if (!exists) {
            InputStream classPathConfig = new ClassPathResource("application.yml").getInputStream();

            Files.copy(classPathConfig, fileSystemConfig);
            log.info("Exported Application Config");
        }
    }
}
