package rocks.cleanstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "rocks.cleanstone")
public class Cleanstone {

    public static void main(String[] args) {
        SpringApplication.run(Cleanstone.class, args).start();
    }
}
