package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.server.RakNetServerListener;
import com.whirvis.jraknet.session.RakNetClientSession;
import com.whirvis.jraknet.util.RakNetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.cleanstone.core.CleanstoneServer;

public class ServerListener implements RakNetServerListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final RakNetNetworking networking;

    public ServerListener(RakNetNetworking networking) {
        this.networking = networking;
    }

    @Override
    public void onClientConnect(RakNetClientSession session) {
        System.out.println("Client from address " + session.getAddress() + " has connected to the server");
        // TODO
    }

    @Override
    public void onClientDisconnect(RakNetClientSession session, String reason) {
        System.out.println("Client from address " + session.getAddress()
                + " has disconnected from the server for the reason \"" + reason + "\"");
        // TODO
    }

    @Override
    public void handleMessage(RakNetClientSession session, RakNetPacket packet, int channel) {
        System.out.println("Client from address " + session.getAddress() + " sent packet with ID "
                + RakNetUtils.toHexStringId(packet) + " on channel " + channel);
        // TODO
    }

    @Override
    public void onThreadException(Throwable throwable) {
        logger.error(CleanstoneServer.getMessage("net.raknet.server-failure",
                networking.getAddress().getHostAddress(), networking.getPort() + ""), throwable);
    }
}
