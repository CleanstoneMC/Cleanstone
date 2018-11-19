package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.identifier.Identifier;
import com.whirvis.jraknet.server.RakNetServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.protocol.Protocol;

import javax.annotation.Nonnull;
import java.net.InetAddress;

@Slf4j
public class RakNetNetworking extends AbstractNetworking implements SmartLifecycle {
    private static int currentServerID = 1;

    protected final Identifier identifier;
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
        log.info("Closing " + protocol.getClass().getSimpleName());
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
