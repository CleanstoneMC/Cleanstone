package rocks.cleanstone.core.utils;

import org.springframework.core.Ordered;

public class EventPriority {

    public static final int MONITOR_RESULT = Ordered.LOWEST_PRECEDENCE - 100;
    public static final int HIGHEST = 400;
    public static final int HIGH = 350;
    public static final int NORMAL = 300;
    public static final int LOW = 250;
    public static final int LOWEST = 200;
    public static final int MONITOR_SOURCE = Ordered.HIGHEST_PRECEDENCE + 100;

    private EventPriority() {
    }
}
