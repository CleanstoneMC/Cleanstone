package rocks.cleanstone.net.raknet;

import com.whirvis.jraknet.session.RakNetClientSession;

import java.net.InetAddress;

import rocks.cleanstone.net.AbstractConnection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.ProtocolState;

public class RakNetConnection extends AbstractConnection {

    private final RakNetClientSession session;

    public RakNetConnection(RakNetClientSession session, InetAddress address, ClientProtocolLayer
            clientProtocolLayer, ProtocolState protocolState) {
        super(address, clientProtocolLayer, protocolState);
        this.session = session;
    }

    @Override
    public void sendPacket(Packet packet) {
        // TODO
    }

    @Override
    public void close() {
        // TODO
    }

    @Override
    public void close(Packet packet) {
        // TODO
    }
}
