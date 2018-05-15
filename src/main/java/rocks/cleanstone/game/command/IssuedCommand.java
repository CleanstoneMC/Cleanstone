package rocks.cleanstone.game.command;

public interface IssuedCommand {
    CommandSender getCommandSender();

    String getCommand();
}
