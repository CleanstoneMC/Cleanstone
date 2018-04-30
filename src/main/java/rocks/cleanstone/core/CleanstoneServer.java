package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;

import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer {

    private static CleanstoneServer INSTANCE;
    @Autowired
    @Qualifier("cleanstoneNetworking")
    protected Networking cleanstoneNetworking;
    @Autowired
    private ApplicationEventPublisher publisher;

    public static CleanstoneServer getInstance() {
        return INSTANCE;
    }

    public static <T> T publishEvent(T event) {
        getInstance().getPublisher().publishEvent(event);
        return event;
    }

    public void init() {
        INSTANCE = this;
    }

    public void destroy() {
        INSTANCE = null;
    }

    public abstract void run();

    public Networking getCleanstoneNetworking() {
        return cleanstoneNetworking;
    }

    public ApplicationEventPublisher getPublisher() {
        return publisher;
    }
}
