package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
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

    @Override
    public void run() {
        ConsoleInputEventPublisher.startInstance();
    }

    @Override
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @EventListener
    public void onConsoleInput(ConsoleInputEvent inputEvent) {
        if (commandRegistry != null) {
            commandRegistry.executeCommand(inputEvent.getInput(), this);
        } else {
            sendMessage("No command registry available");
        }
    }
}
