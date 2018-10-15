package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ClientProtocolLayer;
import rocks.cleanstone.net.protocol.ProtocolState;

import javax.crypto.SecretKey;
import java.net.InetAddress;
import java.util.concurrent.Future;

public interface Connection {

    InetAddress getAddress();

    boolean isCompressionEnabled();

    void setCompressionEnabled(boolean compressionEnabled);

    boolean isEncryptionEnabled();

    void setEncryptionEnabled(boolean encryptionEnabled);

    ClientProtocolLayer getClientProtocolLayer();

    void setClientProtocolLayer(ClientProtocolLayer clientProtocolLayer);

    ProtocolState getProtocolState();

    void setProtocolState(ProtocolState protocolState);

    SecretKey getSharedSecret();

    void setSharedSecret(SecretKey sharedSecret);

    Future<Void> sendPacket(Packet packet);

    Future<Void> close();

    Future<Void> close(Packet packet);

    boolean isClosed();
}
