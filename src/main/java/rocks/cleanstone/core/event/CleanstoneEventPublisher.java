package rocks.cleanstone.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CleanstoneEventPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private CleanstoneEventMulticaster multicaster;

    public synchronized <T> T publishEvent(T event, boolean rethrowExceptions) throws EventExecutionException {
        final long preEventTime = System.currentTimeMillis();
        final String eventName = event.getClass().getSimpleName();
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
