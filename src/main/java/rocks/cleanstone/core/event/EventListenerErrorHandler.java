package rocks.cleanstone.core.event;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventListenerErrorHandler implements ErrorHandler {

    protected boolean rethrowExceptions = false;

    @Override
    public void handleError(@NonNull Throwable throwable) throws EventExecutionException {
        if (throwable instanceof EventCancellationException) {
            throw (EventCancellationException) throwable;
        } else {
            if (rethrowExceptions) {
                throw new EventExecutionException("Error occurred while executing event listener", throwable);
            } else {
                log.error("Error occurred while executing event listener", throwable);
            }
        }
    }

    public void setRethrowExceptions(boolean rethrowExceptions) {
        this.rethrowExceptions = rethrowExceptions;
    }
}
