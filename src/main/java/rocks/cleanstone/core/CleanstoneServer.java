package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;

import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer {

    @Autowired
    @Qualifier("cleanstoneNetworking")
    protected Networking cleanstoneNetworking;

    @Autowired
    private ApplicationEventPublisher publisher;

    public abstract void run();
}
