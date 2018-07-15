package rocks.cleanstone.game.command;

import java.util.Locale;

import rocks.cleanstone.game.chat.message.Text;

public interface MessageRecipient {
    void sendRawMessage(Text message);

    void sendRawMessage(String message);

    void sendMessage(String messageID, Object... args);

    Locale getLocale();
}
