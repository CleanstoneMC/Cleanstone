package rocks.cleanstone.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Custom event publisher with additional timings and event cancellation support
 */
@Slf4j
@Component
public class CleanstoneEventPublisher {

    private final ApplicationEventPublisher publisher;
    private final CleanstoneEventMulticaster multicaster;

    @Autowired
    public CleanstoneEventPublisher(ApplicationEventPublisher publisher, CleanstoneEventMulticaster multicaster) {
        this.publisher = publisher;
        this.multicaster = multicaster;
    }

    /**
     * Publishes an event and calls all event listeners that are listening to it, which might modify or cancel
     * the event.
     *
     * @param event             The event that is being published
     * @param rethrowExceptions Specifies that exceptions inside of event listeners are fatal and should cause
     *                          the system to stop the event publication and throw an {@link
     *                          EventExecutionException} instead of just logging the exception and continuing
     *                          with further event listeners
     */
    public synchronized <T> T publishEvent(T event, boolean rethrowExceptions) throws EventExecutionException {
        long preEventTime = System.currentTimeMillis();
        String eventName = event.getClass().getSimpleName();
        multicaster.getErrorHandler().setRethrowExceptions(rethrowExceptions);
        try {
            publisher.publishEvent(event);
        } catch (EventCancellationException e) {
            log.info("Event " + eventName + " was cancelled");
        }
        if (System.currentTimeMillis() - preEventTime > 50 && !eventName.startsWith("Async")) {
            log.warn("Listeners for non-async event " + eventName + " needed "
                    + (System.currentTimeMillis() - preEventTime)
                    + "ms to complete, this slows down the server");
        }
        return event;
    }

    /**
     * Prevents late event cancellation
     */
    @Order(value = EventAction.MODIFY + 100)
    @EventListener
    public void onCancellableEvent(CancellableEvent e) {
        e.setAllowCancelling(false);
    }
}
