package rocks.cleanstone.console;

import com.google.common.base.Charsets;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandSender;

import java.util.Locale;
import java.util.UUID;
import java.util.function.Consumer;

public class ConsoleSender implements CommandSender {

    private Consumer<String> messageFunction;

    public ConsoleSender(Consumer<String> messageFunction) {
        this.messageFunction = messageFunction;
    }

    @Override
    public void sendRawMessage(Text message) {
        messageFunction.accept(message.getPlainText());
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
    public String getFormattedName() {
        return getID().getName();
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
