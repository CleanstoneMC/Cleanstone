package rocks.cleanstone.core.event;

/**
 * The exception that is thrown when a {@link CancellableEvent} is being cancelled. It should only be caught
 * inside the {@link CleanstoneEventPublisher}.
 */
public class EventCancellationException extends RuntimeException {
    EventCancellationException() {
        super();
    }
}
