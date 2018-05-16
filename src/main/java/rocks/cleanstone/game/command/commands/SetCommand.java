package rocks.cleanstone.game.command.commands;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.command.SimpleSubCommand;
import rocks.cleanstone.game.command.SubCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.SimplePlayerID;

public class SetCommand extends SimpleCommand {

    private final PlayerManager playerManager;

    public SetCommand(PlayerManager playerManager) {
        super("set");
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
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
            subCommandMap.put(subCommand.getName(), subCommand);
        }

        return subCommandMap;
    }

    private static class HealthCommand extends SimpleSubCommand {
        private final SetCommand setCommand;
        private final PlayerManager playerManager;

        public HealthCommand(SetCommand setCommand, PlayerManager playerManager) {
            this.setCommand = setCommand;
            this.playerManager = playerManager;
        }

        @Override
        public Command getParent() {
            return setCommand;
        }

        @Override
        public String getName() {
            return "health";
        }

        @Override
        public int getMinimumParameters() {
            return 1;
        }

        @Override
        public void execute(CommandMessage commandMessage) {
            String playerName = commandMessage.getParameters().get(0);
            Player player = playerManager.getOnlinePlayer(playerName);

            if (player == null) {
                if (commandMessage.getCommandSender() instanceof Player) {
                    commandMessage.getCommandSender().sendMessage(new Chat("Player not found"));
                } else {
                    logger.info("Could not find Player \"{}\" for command \"{}\"", playerName, commandMessage.getFullMessage());
                }

                return;
            }

            //TODO: Set Health

            if (commandMessage.getCommandSender() instanceof Player) {
                commandMessage.getCommandSender().sendMessage(new Chat("Health set"));
            } else {
                logger.info("Health for Player \"{}\" set", playerName);
            }
        }
    }

    private static class NameCommand extends SimpleSubCommand {
        private final SetCommand setCommand;
        private final PlayerManager playerManager;

        public NameCommand(SetCommand setCommand, PlayerManager playerManager) {
            this.setCommand = setCommand;
            this.playerManager = playerManager;
        }

        @Override
        public Command getParent() {
            return setCommand;
        }

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public int getMinimumParameters() {
            return 2;
        }

        @Override
        public void execute(CommandMessage commandMessage) {
            String playerName = commandMessage.getParameters().get(0);
            String newName = commandMessage.getParameters().get(1);

            Player player = playerManager.getOnlinePlayer(playerName);

            if (player == null) {
                if (commandMessage.getCommandSender() instanceof Player) {
                    ((Player) commandMessage.getCommandSender()).sendMessage(new Chat("Player not found"));
                } else {
                    logger.info("Could not find Player \"{}\" for command \"{}\"", playerName, commandMessage.getFullMessage());
                }

                return;
            }

            PlayerID playerID = player.getId();
            if (playerID instanceof SimplePlayerID) {
                ((SimplePlayerID) playerID).setName(newName);
            } else {
                commandMessage.getCommandSender().sendMessage(new Chat("Cannot change name of Player \"{}\"", playerName));
                return;
            }

            if (commandMessage.getCommandSender() instanceof Player) {
                commandMessage.getCommandSender().sendMessage(new Chat("Player \"{}\" is now named \"{}\"", playerName, newName));
            } else {
                logger.info("Player \"{}\" is now named \"{}\"", playerName, newName);
            }
        }
    }
}
