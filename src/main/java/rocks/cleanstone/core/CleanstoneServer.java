package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.net.Networking;

public abstract class CleanstoneServer {
    @Autowired
    protected Networking networking;

    public abstract void run();
}
