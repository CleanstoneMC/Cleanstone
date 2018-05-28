package rocks.cleanstone.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.ErrorHandler;

public class EventListenerErrorHandler implements ErrorHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected boolean rethrowExceptions = false;

    @Override
    public void handleError(@NonNull Throwable throwable) throws EventExecutionException {
        if (throwable instanceof EventCancellationException) {
            throw (EventCancellationException) throwable;
        } else {
            if (rethrowExceptions) {
                throw new EventExecutionException("Error occurred while executing event listener", throwable);
            } else {
                logger.error("Error occurred while executing event listener", throwable);
            }
        }
    }

    public void setRethrowExceptions(boolean rethrowExceptions) {
        this.rethrowExceptions = rethrowExceptions;
    }
}
