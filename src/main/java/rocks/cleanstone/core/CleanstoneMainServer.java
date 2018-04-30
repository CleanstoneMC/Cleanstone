package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

import rocks.cleanstone.net.minecraft.MinecraftNetworking;

public class CleanstoneMainServer extends CleanstoneServer {

    @Autowired
    @Qualifier("minecraftNetworking")
    protected MinecraftNetworking minecraftNetworking;

    private Set<ExternalServer> externalServers;

    public void init() {
        System.out.println("init");
    }

    public void run() {
        System.out.println("run");
        cleanstoneNetworking.start();
        minecraftNetworking.start();
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public MinecraftNetworking getMinecraftNetworking() {
        return minecraftNetworking;
    }
}
