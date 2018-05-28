package rocks.cleanstone.core.event;

public class EventExecutionException extends RuntimeException {
    public EventExecutionException() {
        super();
    }

    public EventExecutionException(String message) {
        super(message);
    }

    public EventExecutionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
