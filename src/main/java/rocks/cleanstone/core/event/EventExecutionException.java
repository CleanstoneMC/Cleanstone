package rocks.cleanstone.core.event;

/**
 * The exception that wraps exceptions that are thrown inside of an event listener. This is only used when the
 * event publisher specified that exceptions are fatal and should cause the system to stop the event
 * publication and throw this exception instead of just logging the exception and continuing with further
 * event listeners.
 */
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
