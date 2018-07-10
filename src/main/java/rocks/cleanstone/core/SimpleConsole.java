package rocks.cleanstone.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandRegistry;

public class SimpleConsole implements ConsoleSender {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;
    private boolean running = true;
    private static BufferedReader inputReader;
    private static final BlockingQueue<String> lines = new LinkedBlockingQueue<>();

    @Override
    public void sendMessage(Text message) {
        logger.info(message.getPlainText());
    }

    @Override
    public void sendMessage(String message) {
        logger.info(message);
    }

    @Async
    @Override
    public void run() {
        spawnInputReader();

        try {
            while (running) {
                String input = lines.poll(200, TimeUnit.MILLISECONDS);
                if (input == null) {
                    continue;
                }
                if (commandRegistry != null) {
                    commandRegistry.executeCommand(input, this);
                } else {
                    sendMessage("No command registry available");
                }
            }
        } catch (InterruptedException ignored) {
            // throws when application context closes
        }
    }

    private void spawnInputReader() {
        if (inputReader != null) { // keep input reader across spring restarts
            return;
        }

        inputReader = new BufferedReader(new InputStreamReader(System.in));

        Thread inputReaderThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    String input = inputReader.readLine();
                    if (input == null) {
                        throw new IOException("Console input reached EOS");
                    }
                    lines.add(input);
                }
            } catch (IOException e) {
                logger.error("Error occurred while reading console input", e);
            }
        });
        inputReaderThread.setDaemon(true);
        inputReaderThread.start();
    }

    private void destroy() {
        running = false;
    }

    @Override
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }
}
