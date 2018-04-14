package rocks.cleanstone.net.packet.protocol;

public interface ProtocolType {
    int getTypeId();

    Class<? extends Protocol> getProtocolClass();
}
