package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.player.PlayerManager;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class))
@ImportResource("classpath:rocks/cleanstone/CleanstoneSubServer.xml")
public class CleanstoneSubServer extends CleanstoneServer {

    private ExternalServer mainServer;

    @Autowired
    public CleanstoneSubServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig, PlayerManager playerManager, ConfigurableApplicationContext context) {
        super(cleanstoneConfig, minecraftConfig, playerManager, context);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}