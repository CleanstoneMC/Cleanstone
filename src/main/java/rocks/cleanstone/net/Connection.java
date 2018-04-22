package rocks.cleanstone.net;

import io.netty.channel.Channel;
import rocks.cleanstone.net.packet.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.ProtocolState;

import javax.crypto.SecretKey;
import java.net.InetAddress;

public class Connection {

    private final InetAddress address;
    private Channel channel;
    private boolean compressionEnabled = false, encryptionEnabled = false;
    private ClientProtocolLayer clientProtocolLayer;
    private SecretKey sharedSecret;
    private ProtocolState protocolState;

    public Connection(InetAddress address, Channel channel) {
        this.address = address;
        this.channel = channel;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
