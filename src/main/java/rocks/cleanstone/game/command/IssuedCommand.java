package rocks.cleanstone.game.command;

import java.util.List;

public interface IssuedCommand {
    CommandSender getCommandSender();

    String getFullCommand();

    String getCommand();

    List<String> getParameter();
}
