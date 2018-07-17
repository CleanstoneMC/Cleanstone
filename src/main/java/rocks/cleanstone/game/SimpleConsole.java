package rocks.cleanstone.game;

import com.google.common.base.Charsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.util.Locale;
import java.util.UUID;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.ConsoleInputEvent;
import rocks.cleanstone.core.ConsoleInputEventPublisher;
import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandRegistry;

public class SimpleConsole implements ConsoleSender {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;

    @Override
    public void sendRawMessage(Text message) {
        logger.info(message.getPlainText());
    }

    @Override
    public void sendRawMessage(String message) {
        sendRawMessage(Text.of(message));
    }

    @Override
    public void sendMessage(String messageID, Object... args) {
        sendRawMessage(Text.ofLocalized(messageID, getLocale(), args));
    }

    @Override
    public Locale getLocale() {
        return CleanstoneServer.getDefaultLocale();
    }

    @Override
    public Identity getID() {
        return new ConsoleIdentity(UUID.nameUUIDFromBytes("Console:0".getBytes(Charsets.UTF_8)));
    }

    @Override
    public String getName() {
        return getID().getName();
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
            sendRawMessage("No command registry available");
        }
    }

    public static class ConsoleIdentity implements Identity {
        private final UUID uuid;

        public ConsoleIdentity(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public String getName() {
            return CleanstoneServer.getMessage("game.console-name");
        }

        @Override
        public UUID getUUID() {
            return uuid;
        }
    }
}
