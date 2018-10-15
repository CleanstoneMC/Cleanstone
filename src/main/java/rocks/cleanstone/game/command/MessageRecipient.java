package rocks.cleanstone.game.command;

import rocks.cleanstone.game.chat.message.Text;

import java.util.Locale;

public interface MessageRecipient {
    void sendRawMessage(Text message);

    void sendRawMessage(String message);

    void sendMessage(String messageID, Object... args);

    Locale getLocale();
}
