package rocks.cleanstone.game.command;

import rocks.cleanstone.game.chat.message.Chat;

public interface CommandSender {
    void sendMessage(Chat message);

    void sendMessage(String message);
}
