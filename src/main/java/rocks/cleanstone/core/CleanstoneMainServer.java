package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

import java.util.Set;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class), basePackages = "rocks.cleanstone")
@Component("cleanstoneMainServer")
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
