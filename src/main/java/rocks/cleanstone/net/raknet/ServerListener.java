package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.server.RakNetServerListener;
import com.whirvis.jraknet.session.RakNetClientSession;
import com.whirvis.jraknet.util.RakNetUtils;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.core.CleanstoneServer;

@Slf4j
public class ServerListener implements RakNetServerListener {
    private final RakNetNetworking networking;

    public ServerListener(RakNetNetworking networking) {
        this.networking = networking;
    }

    @Override
    public void onClientConnect(RakNetClientSession session) {
        log.info("Client from address " + session.getAddress() + " has connected to the server");
        // TODO
    }

    @Override
    public void onClientDisconnect(RakNetClientSession session, String reason) {
        log.info("Client from address " + session.getAddress()
                + " has disconnected from the server for the reason \"" + reason + "\"");
        // TODO
    }

    @Override
    public void handleMessage(RakNetClientSession session, RakNetPacket packet, int channel) {
        log.info("Client from address " + session.getAddress() + " sent packet with ID "
                + RakNetUtils.toHexStringId(packet) + " on channel " + channel);
        // TODO
    }

    @Override
    public void onThreadException(Throwable throwable) {
        log.error(CleanstoneServer.getMessage("net.raknet.server-failure",
                networking.getAddress().getHostAddress(), networking.getPort() + ""), throwable);
    }

    @Override
    public void onServerStart() {
        log.info(CleanstoneServer.getMessage("net.raknet.bind-successful",
                networking.getProtocol().getClass().getSimpleName(), networking.getAddress(),
                networking.getPort() + ""));
    }
}
