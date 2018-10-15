package rocks.cleanstone.net.raknet;

import com.google.common.util.concurrent.Futures;
import com.whirvis.jraknet.session.RakNetClientSession;
import com.whirvis.jraknet.session.RakNetState;
import rocks.cleanstone.net.AbstractConnection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.ProtocolState;

import java.net.InetAddress;
import java.util.concurrent.Future;

public class RakNetConnection extends AbstractConnection {

    private final RakNetClientSession session;

    public RakNetConnection(RakNetClientSession session, InetAddress address, ClientProtocolLayer
            clientProtocolLayer, ProtocolState protocolState) {
        super(address, clientProtocolLayer, protocolState);
        this.session = session;
    }

    @Override
    public Future<Void> sendPacket(Packet packet) {
        // TODO
        return Futures.immediateCancelledFuture();
    }

    @Override
    public Future<Void> close() {
        // TODO
        return Futures.immediateCancelledFuture();
    }

    @Override
    public Future<Void> close(Packet packet) {
        // TODO
        return Futures.immediateCancelledFuture();
    }

    @Override
    public boolean isClosed() {
        return session.getState() == RakNetState.DISCONNECTED;
    }
}
