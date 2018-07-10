package rocks.cleanstone.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Set;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.player.PlayerManager;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class))
@ImportResource("classpath:rocks/cleanstone/CleanstoneMainServer.xml")
public class CleanstoneMainServer extends CleanstoneServer {

    private final ConsoleSender console;
    private Set<ExternalServer> externalServers;

    public CleanstoneMainServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig,
                                ConsoleSender console, PlayerManager playerManager) {
        super(cleanstoneConfig, minecraftConfig, playerManager);
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
