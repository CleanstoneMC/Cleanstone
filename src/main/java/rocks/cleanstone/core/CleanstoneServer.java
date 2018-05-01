package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer implements ApplicationRunner {

    private static CleanstoneServer INSTANCE;

    protected final CleanstoneConfig cleanstoneConfig;
    protected final MinecraftConfig minecraftConfig;
    protected final Networking cleanstoneNetworking;
    @Autowired
    protected ApplicationEventPublisher publisher;

    public void init() {
        INSTANCE = this;
    }

    protected CleanstoneServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig, Networking cleanstoneNetworking) {
        this.cleanstoneConfig = cleanstoneConfig;
        this.minecraftConfig = minecraftConfig;
        this.cleanstoneNetworking = cleanstoneNetworking;
    }

    public static <T> T publishEvent(T event) {
        getInstance().getPublisher().publishEvent(event);
        return event;
    }

    public void destroy() {
        INSTANCE = null;
    }

    public static CleanstoneServer getInstance() {
        return INSTANCE;
    }

    public Networking getCleanstoneNetworking() {
        return cleanstoneNetworking;
    }

    public ApplicationEventPublisher getPublisher() {
        return publisher;
    }

    public CleanstoneConfig getCleanstoneConfig() {
        return cleanstoneConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }
}
