package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.identifier.Identifier;
import com.whirvis.jraknet.server.RakNetServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.protocol.Protocol;

import javax.annotation.Nonnull;
import java.net.InetAddress;

public class RakNetNetworking extends AbstractNetworking implements SmartLifecycle {

    private static int currentServerID = 1;

    protected final Identifier identifier;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RakNetServer server;
    private boolean running = false;

    public RakNetNetworking(int port, InetAddress address, Protocol protocol, Identifier identifier) {
        super(port, address, protocol);
        this.identifier = identifier;
    }

    @Override
    public void start() {
        server = new RakNetServer(port, Integer.MAX_VALUE, identifier);
        server.addListener(new ServerListener(this));
        server.startThreaded().setName("rakNetServer-" + currentServerID++);
        running = true;
    }

    @Override
    public void stop() {
        logger.info("Closing " + protocol.getClass().getSimpleName());
        server.shutdown();
        running = false;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(@Nonnull Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 10;
    }
}
