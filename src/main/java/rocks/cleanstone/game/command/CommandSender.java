package rocks.cleanstone.game.command;

import rocks.cleanstone.game.chat.message.Text;

public interface CommandSender {
    void sendMessage(Text message);

    void sendMessage(String message);
}
