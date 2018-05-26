package rocks.cleanstone.core.event;

public class IllegalEventCancellationException extends RuntimeException {
    public IllegalEventCancellationException() {
        super();
    }

    public IllegalEventCancellationException(String message) {
        super(message);
    }
}
