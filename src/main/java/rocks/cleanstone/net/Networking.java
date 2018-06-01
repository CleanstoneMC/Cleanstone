package rocks.cleanstone.net;

import rocks.cleanstone.net.protocol.Protocol;

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
