package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer {
    @Autowired
    protected Networking networking;

    @Autowired
    private ApplicationEventPublisher publisher;

    public abstract void run();
}
