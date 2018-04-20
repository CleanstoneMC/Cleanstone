package rocks.cleanstone.net.utils;

import java.io.IOException;

public class NotEnoughReadableBytesException extends IOException {

    public NotEnoughReadableBytesException() {
        super();
    }

    public NotEnoughReadableBytesException(String message) {
        super("We need more bytes!" + message);
    }
}
