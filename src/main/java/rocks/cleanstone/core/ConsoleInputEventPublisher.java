package rocks.cleanstone.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsoleInputEventPublisher implements Lifecycle {
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
                    if (CleanstoneServer.getInstance() == null) {
                        continue;
                    }
                    CleanstoneServer.publishEvent(new ConsoleInputEvent(input));
                }
            } catch (IOException e) {
                log.error("Error occurred while reading console input", e);
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
