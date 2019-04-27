package rocks.cleanstone.core.event;

/**
 * The exception that is thrown when a {@link CancellableEvent} is being cancelled by an event listener that
 * is using an incorrect event priority as defined by the {@link EventAction} boundaries.
 */
public class IllegalEventCancellationException extends EventExecutionException {
    public IllegalEventCancellationException() {
        super();
    }

    public IllegalEventCancellationException(String message) {
        super(message);
    }
}
