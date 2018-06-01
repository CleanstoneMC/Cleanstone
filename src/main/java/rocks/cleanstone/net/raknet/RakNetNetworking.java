package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.identifier.Identifier;
import com.whirvis.jraknet.server.RakNetServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.net.InetAddress;

import rocks.cleanstone.net.AbstractNetworking;
import rocks.cleanstone.net.protocol.Protocol;

public class RakNetNetworking extends AbstractNetworking {

    private static int currentServerID = 0;

    protected final Identifier identifier;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RakNetServer server;

    public RakNetNetworking(int port, InetAddress address, Protocol protocol, Identifier identifier) {
        super(port, address, protocol);
        this.identifier = identifier;
    }

    public void init() {
        server = new RakNetServer(port, Integer.MAX_VALUE, identifier);
        server.addListener(new ServerListener(this));
        server.startThreaded().setName("RakNetServer-" + currentServerID++);
    }

    @EventListener
    public void onPreDestroy(ContextClosedEvent e) {
        logger.info("Closing " + protocol.getClass().getSimpleName());
        server.shutdown();
    }
}
