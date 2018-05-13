package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.protocol.Protocol;

import java.net.InetAddress;
import java.security.KeyPair;
import java.util.Collection;

public interface Networking {

    int getPort();

    InetAddress getAddress();

    Protocol getProtocol();

    KeyPair getKeyPair();

    Collection<String> getClientAddressBlacklist();
}
