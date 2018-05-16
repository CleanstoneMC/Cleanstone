package rocks.cleanstone.game.command;

import java.util.List;

public interface CommandMessage {
    CommandSender getCommandSender();

    String getFullMessage();

    String getCommandName();

    List<String> getParameters();
}
