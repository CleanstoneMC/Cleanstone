package rocks.cleanstone.core.event;

import org.springframework.core.Ordered;

/**
 * Essential event priorities (spring event order) for event listeners which act as boundaries for different
 * event publication stages. Generally event listeners with higher priorities (MONITOR_RESULT) are called
 * later than event listeners with lower priorities (MODIFY, PREVENT).
 * <p></p>
 * Event listeners which neither modify fields of the event object nor cancel the event shall use the
 * MONITOR_RESULT action as their event listener priority to observe the result of the event publication with
 * all modifications already applied. The event was not cancelled and cant be cancelled or modified anymore if
 * a listener with this priority has been called.
 * </p><p>
 * Event listeners which modify or might modify fields of the event object shall use the MODIFY action as
 * their event listener priority. There might be further modifications to the event afterwards.
 * </p>
 * Event listeners which might modify or <b>cancel</b> the event shall use the PREVENT action as their event
 * listener priority.
 */
public class EventAction {

    public static final int MONITOR_RESULT = Ordered.LOWEST_PRECEDENCE;
    public static final int MODIFY = 0;
    public static final int PREVENT = Ordered.HIGHEST_PRECEDENCE + 100;

    private EventAction() {
    }
}
