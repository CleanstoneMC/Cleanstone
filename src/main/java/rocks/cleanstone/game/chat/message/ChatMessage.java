package rocks.cleanstone.game.chat.message;

import rocks.cleanstone.game.command.CommandSender;

public class ChatMessage extends Text {
    public ChatMessage(CommandSender sender, String message) {
        super(sender.getName() + ": " + message);
    }
}
