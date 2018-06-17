package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandRegistry;

public class SimpleConsole implements ConsoleSender {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;

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
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = inputReader.readLine();
                if (input == null) {
                    throw new IOException("Console input reached EOS");
                }
                if (commandRegistry != null)
                    commandRegistry.executeCommand(input, this);
                else sendMessage("No command registry available");
            }
        } catch (IOException e) {
            logger.error("Error occurred while reading console input", e);
        }
    }

    @Override
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }
}
