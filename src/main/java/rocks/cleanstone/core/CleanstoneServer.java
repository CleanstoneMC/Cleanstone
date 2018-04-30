package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;

import rocks.cleanstone.Cleanstone;
import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer {


    @Autowired
    @Qualifier("cleanstoneNetworking")
    protected Networking cleanstoneNetworking;
    @Autowired
    private ApplicationEventPublisher publisher;

    public static <T> T publishEvent(T event) {
        CleanstoneApplication.getInstance().getPublisher().publishEvent(event);
        return event;
    }

    public abstract void run();

    public Networking getCleanstoneNetworking() {
        return cleanstoneNetworking;
    }

    public ApplicationEventPublisher getPublisher() {
        return publisher;
    }
}
