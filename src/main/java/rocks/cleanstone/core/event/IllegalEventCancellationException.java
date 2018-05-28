package rocks.cleanstone.core.event;

public class IllegalEventCancellationException extends EventExecutionException {
    public IllegalEventCancellationException() {
        super();
    }

    public IllegalEventCancellationException(String message) {
        super(message);
    }
}
