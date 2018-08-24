package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputEventPublisher {
    // can not use a spring bean, because the stdin reader needs to persist across spring context restarts
    private static ConsoleInputEventPublisher INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(ConsoleInputEventPublisher.class);

    private ConsoleInputEventPublisher() {
        init();
    }

    @PostConstruct
    private void init() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        Thread inputReaderThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    String input = inputReader.readLine();
                    if (input == null) {
                        throw new IOException("Console input reached EOS");
                    }

                    // ignore input while server is not running
                    if (CleanstoneServer.getInstance() == null)
                        continue;
                    CleanstoneServer.publishEvent(new ConsoleInputEvent(input));
                }
            } catch (IOException e) {
                logger.error("Error occurred while reading console input", e);
            }
        });
        inputReaderThread.setDaemon(true);
        inputReaderThread.start();
    }

    public static synchronized void startInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConsoleInputEventPublisher();
        }
    }
}
