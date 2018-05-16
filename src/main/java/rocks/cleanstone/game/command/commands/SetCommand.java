package rocks.cleanstone.game.command.commands;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.command.*;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.SimplePlayerID;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SetCommand extends SimpleCommand {

    private final PlayerManager playerManager;

    public SetCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public String getCommandString() {
        return "set";
    }

    @Override
    public void execute(IssuedCommand issuedCommand) {
    }

    @Nullable
    @Override
    public Map<String, SubCommand> getSubCommands() {
        Map<String, SubCommand> subCommandMap = new HashMap<>();

        SubCommand[] subCommands = new SubCommand[]{
                new HealthCommand(this, playerManager),
                new NameCommand(this, playerManager)
        };

        for (SubCommand subCommand : subCommands) {
            subCommandMap.put(subCommand.getCommandString(), subCommand);
        }

        return subCommandMap;
    }

    private class HealthCommand extends SimpleSubCommand {
        private final SetCommand setCommand;
        private final PlayerManager playerManager;

        public HealthCommand(SetCommand setCommand, PlayerManager playerManager) {
            this.setCommand = setCommand;
            this.playerManager = playerManager;
        }

        @Override
        public Command getMainCommand() {
            return setCommand;
        }

        @Override
        public String getCommandString() {
            return "health";
        }

        @Override
        public int neededParameter() {
            return 1;
        }

        @Override
        public void execute(IssuedCommand issuedCommand) {
            String playerName = issuedCommand.getParameter().get(0);
            Player player = playerManager.getOnlinePlayer(playerName);

            if (player == null) {
                if (issuedCommand.getCommandSender() instanceof Player) {
                    ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Player not found"));
                } else {
                    logger.info("Could not find Player \"{}\" for command \"{}\"", playerName, issuedCommand.getFullCommand());
                }

                return;
            }

            //TODO: Set Health

            if (issuedCommand.getCommandSender() instanceof Player) {
                ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Health set"));
            } else {
                logger.info("Health for Player \"{}\" set", playerName);
            }
        }
    }

    private class NameCommand extends SimpleSubCommand {
        private final SetCommand setCommand;
        private final PlayerManager playerManager;

        public NameCommand(SetCommand setCommand, PlayerManager playerManager) {
            this.setCommand = setCommand;
            this.playerManager = playerManager;
        }

        @Override
        public Command getMainCommand() {
            return setCommand;
        }

        @Override
        public String getCommandString() {
            return "name";
        }

        @Override
        public int neededParameter() {
            return 2;
        }

        @Override
        public void execute(IssuedCommand issuedCommand) {
            String playerName = issuedCommand.getParameter().get(0);
            String newName = issuedCommand.getParameter().get(1);

            Player player = playerManager.getOnlinePlayer(playerName);

            if (player == null) {
                if (issuedCommand.getCommandSender() instanceof Player) {
                    ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Player not found"));
                } else {
                    logger.info("Could not find Player \"{}\" for command \"{}\"", playerName, issuedCommand.getFullCommand());
                }

                return;
            }

            PlayerID playerID = player.getId();
            if (playerID instanceof SimplePlayerID) {
                ((SimplePlayerID) playerID).setName(newName);
            } else {
                ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Cannot change name of Player \"{}\"", playerName));
                return;
            }

            if (issuedCommand.getCommandSender() instanceof Player) {
                ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Player \"{}\" is now named \"{}\"", playerName, newName));
            } else {
                logger.info("Player \"{}\" is now named \"{}\"", playerName, newName);
            }
        }
    }
}
