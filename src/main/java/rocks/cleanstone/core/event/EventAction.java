package rocks.cleanstone.core.event;

import org.springframework.core.Ordered;

public class EventAction {

    public static final int MONITOR_RESULT = Ordered.LOWEST_PRECEDENCE;
    public static final int MODIFY = 0;
    public static final int PREVENT = Ordered.HIGHEST_PRECEDENCE + 100;

    private EventAction() {
    }
}
