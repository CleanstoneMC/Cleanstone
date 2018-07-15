package rocks.cleanstone.net;

import java.net.InetAddress;
import java.util.concurrent.Future;

import javax.crypto.SecretKey;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.ProtocolState;

public abstract class AbstractConnection implements Connection {

    private final InetAddress address;
    private boolean compressionEnabled = false, encryptionEnabled = false;
    private ClientProtocolLayer clientProtocolLayer;
    private SecretKey sharedSecret;
    private ProtocolState protocolState;

    public AbstractConnection(InetAddress address, ClientProtocolLayer clientProtocolLayer,
                              ProtocolState protocolState) {
        this.address = address;
        this.clientProtocolLayer = clientProtocolLayer;
        this.protocolState = protocolState;
    }

    public InetAddress getAddress() {
        return address;
    }

    public boolean isCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }

    public boolean isEncryptionEnabled() {
        return encryptionEnabled;
    }

    public void setEncryptionEnabled(boolean encryptionEnabled) {
        this.encryptionEnabled = encryptionEnabled;
    }

    public ClientProtocolLayer getClientProtocolLayer() {
        return clientProtocolLayer;
    }

    public void setClientProtocolLayer(ClientProtocolLayer clientProtocolLayer) {
        this.clientProtocolLayer = clientProtocolLayer;
    }

    public ProtocolState getProtocolState() {
        return protocolState;
    }

    public void setProtocolState(ProtocolState protocolState) {
        this.protocolState = protocolState;
    }

    public SecretKey getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(SecretKey sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public abstract Future<Void> sendPacket(Packet packet);

    public abstract Future<Void> close();

    public abstract Future<Void> close(Packet packet);
}
