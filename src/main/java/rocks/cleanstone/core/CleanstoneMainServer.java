package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Set;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.game.chat.ConsoleSender;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class))
@ImportResource("classpath:rocks/cleanstone/CleanstoneMainServer.xml")
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
