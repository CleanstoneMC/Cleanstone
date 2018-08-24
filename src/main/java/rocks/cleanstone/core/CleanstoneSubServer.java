package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(SpringBootApplication.class), basePackages = "rocks.cleanstone")
@EnableCaching
@EnableAsync
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@EnableScheduling
@Component("cleanstoneSubServer")
public class CleanstoneSubServer extends CleanstoneServer {

    private ExternalServer mainServer;

    @Autowired
    public CleanstoneSubServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        super(cleanstoneConfig, minecraftConfig);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}