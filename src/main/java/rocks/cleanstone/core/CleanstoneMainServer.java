package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

import java.util.Set;

@SpringBootApplication
@Component("cleanstoneMainServer")
@Profile(value = "mainServer")
public class CleanstoneMainServer extends CleanstoneServer {

    private Set<ExternalServer> externalServers;

    @Autowired
    public CleanstoneMainServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        super(cleanstoneConfig, minecraftConfig);
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}
