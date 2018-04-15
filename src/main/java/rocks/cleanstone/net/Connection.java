package rocks.cleanstone.net;

import java.net.InetAddress;

import javax.crypto.SecretKey;

import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;

public class Connection {

    private final InetAddress address;
    private boolean compressionEnabled = false, encryptionEnabled = false;
    private ClientProtocolLayer clientProtocolLayer;
    private SecretKey sharedSecret;

    public Connection(InetAddress address) {
        this.address = address;
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

    public SecretKey getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(SecretKey sharedSecret) {
        this.sharedSecret = sharedSecret;
    }
}
