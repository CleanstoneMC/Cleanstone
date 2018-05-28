package rocks.cleanstone.core.event;

import com.google.common.base.Preconditions;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.util.ErrorHandler;

public class CleanstoneEventMulticaster extends SimpleApplicationEventMulticaster {
    public CleanstoneEventMulticaster(EventListenerErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
    }

    @Override
    public EventListenerErrorHandler getErrorHandler() {
        return (EventListenerErrorHandler) super.getErrorHandler();
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        Preconditions.checkArgument(errorHandler instanceof EventListenerErrorHandler,
                "errorHandler must be an EventListenerErrorHandler");
        super.setErrorHandler(errorHandler);
    }
}
