package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleInputEventPublisher implements Lifecycle {
    private final Logger logger = LoggerFactory.getLogger(ConsoleInputEventPublisher.class);
    private static boolean running = false;

    @Override
    public void start() {
        running = true;

        final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        final Thread inputReaderThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    final String input = inputReader.readLine();
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

    @Override
    public void stop() {
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
