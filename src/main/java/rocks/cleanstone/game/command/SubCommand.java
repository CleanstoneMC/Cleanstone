package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

public interface SubCommand extends Command {
    Command getMainCommand();
}
