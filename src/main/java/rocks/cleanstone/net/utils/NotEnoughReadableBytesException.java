package rocks.cleanstone.net.utils;

import java.io.IOException;

public class NotEnoughReadableBytesException extends IOException {

    public NotEnoughReadableBytesException() {
        super("We need more bytes!");
    }

    public NotEnoughReadableBytesException(String message) {
        super("We need more bytes! " + message);
    }
}
