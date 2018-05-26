package rocks.cleanstone.core.event;

public abstract class CancellableEvent {

    private boolean cancelled = false;
    private boolean allowCancelling = true;

    /**
     * Cancels the current event and prevents further EventListeners from being called by raising an exception
     * Event listeners calling this *MUST* have an order number of roughly EventAction.PREVENT and cannot be async
     */
    public void cancel() {
        if (!allowCancelling) throw new IllegalEventCancellationException(
                "Cannot cancel event at this time; the event listener needs an order of roughly EventAction.PREVENT");
        cancelled = true;
        throw new EventCancellationException();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    void setAllowCancelling(boolean allow) {
        allowCancelling = allow;
    }
}
