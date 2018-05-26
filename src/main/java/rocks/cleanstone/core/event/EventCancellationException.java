package rocks.cleanstone.core.event;

public class EventCancellationException extends RuntimeException {
    public EventCancellationException() {
        super();
    }

    public EventCancellationException(String message) {
        super(message);
    }
}
