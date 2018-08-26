package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.game.chat.ConsoleSender;

import java.util.Set;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class), basePackages = "rocks.cleanstone")
@EnableCaching
@EnableAsync
@EnableAutoConfiguration
@EnableScheduling
@Component("cleanstoneMainServer")
public class CleanstoneMainServer extends CleanstoneServer {

    private final ConsoleSender console;
    private Set<ExternalServer> externalServers;

    @Autowired
    public CleanstoneMainServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig,
                                ConsoleSender console) {
        super(cleanstoneConfig, minecraftConfig);
        this.console = console;
    }

    @Override
    public void run(ApplicationArguments args) {
        console.run();
    }

    public ConsoleSender getConsole() {
        return console;
    }
}
