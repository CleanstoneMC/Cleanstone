package rocks.cleanstone.game.command;

public interface SubCommand extends Command {
    Command getMainCommand();
}
