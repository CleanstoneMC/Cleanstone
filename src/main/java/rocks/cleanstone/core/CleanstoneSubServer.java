package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

/**
 * A sub Cleanstone server instance which accepts workload allocated by the {@link CleanstoneMainServer}.
 * Currently unused.
 */
@SpringBootApplication
@Component("cleanstoneSubServer")
@Profile(value = "subServer")
public class CleanstoneSubServer extends CleanstoneServer {

    @Autowired
    public CleanstoneSubServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        super(cleanstoneConfig, minecraftConfig);
    }
}