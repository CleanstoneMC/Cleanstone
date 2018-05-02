package rocks.cleanstone.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Set;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class))
@ImportResource("classpath:rocks/cleanstone/CleanstoneMainServer.xml")
public class CleanstoneMainServer extends CleanstoneServer {

    protected final MinecraftNetworking minecraftNetworking;

    private Set<ExternalServer> externalServers;

    protected CleanstoneMainServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig,
                                   Networking cleanstoneNetworking, MinecraftNetworking minecraftNetworking) {
        super(cleanstoneConfig, minecraftConfig, cleanstoneNetworking);
        this.minecraftNetworking = minecraftNetworking;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    public MinecraftNetworking getMinecraftNetworking() {
        return minecraftNetworking;
    }

}
