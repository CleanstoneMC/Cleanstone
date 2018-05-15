package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

public interface SubCommand {
    Command getMainCommand();

    String getSubCommandString();

    Permission getMinimalPermission();
}
