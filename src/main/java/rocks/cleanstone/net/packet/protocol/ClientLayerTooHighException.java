package rocks.cleanstone.net.packet.protocol;

public class ClientLayerTooHighException extends Exception {
    public ClientLayerTooHighException(String message) {
        super(message);
    }
}