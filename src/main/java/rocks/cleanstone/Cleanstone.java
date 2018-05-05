package rocks.cleanstone;

import org.springframework.boot.SpringApplication;

import rocks.cleanstone.core.CleanstoneMainServer;

public class Cleanstone {

    public static void main(String[] args) {
        //TODO: Add SubServer check
        SpringApplication.run(CleanstoneMainServer.class, args).registerShutdownHook();
    }
}
